package com.wanichnun.lab.mongodata.repository;

import com.wanichnun.lab.mongodata.document.Apartment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApartmentRepository extends PagingAndSortingRepository<Apartment, Long> {
}
