package com.wanichnun.lab.mongodata.service;

import com.wanichnun.lab.mongodata.document.Apartment;
import com.wanichnun.lab.mongodata.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentService {
    private ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<Apartment> getAll() {
        return (List<Apartment>) apartmentRepository.findAll();
    }

    public Page<Apartment> getAllAndSortByNo(Integer page, Integer perPage, String direction, String field) {

        Pageable pageable = new PageRequest(page, perPage, Sort.Direction.fromString(direction), field);

        return apartmentRepository.findAll(pageable);
    }
}
