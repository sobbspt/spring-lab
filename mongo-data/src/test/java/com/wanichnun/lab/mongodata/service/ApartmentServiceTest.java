package com.wanichnun.lab.mongodata.service;

import com.wanichnun.lab.mongodata.document.Apartment;
import com.wanichnun.lab.mongodata.repository.ApartmentRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ApartmentServiceTest {

    private ApartmentService apartmentService;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Before
    public void setUp() {
        apartmentService = new ApartmentService(apartmentRepository);
    }

    @After
    public void tearDown() {
        apartmentRepository.deleteAll();
    }

    @Test
    public void test_getAll() {
        Apartment apartment1 = new Apartment();
        apartment1.setNo(1L);
        apartment1.setName("A 1");
        apartment1.setAddress("ADDR 1");

        Apartment apartment2= new Apartment();
        apartment2.setNo(2L);
        apartment2.setName("A 2");
        apartment2.setAddress("ADDR 2");

        apartmentRepository.save(Arrays.asList(apartment1, apartment2));

        List<Apartment> result = apartmentService.getAll();
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void test_getAllAndSort() {
        Apartment apartment1 = new Apartment();
        apartment1.setNo(1L);
        apartment1.setName("A 1");
        apartment1.setAddress("ADDR 1");

        Apartment apartment2 = new Apartment();
        apartment2.setNo(2L);
        apartment2.setName("A 2");
        apartment2.setAddress("ADDR 2");

        Apartment apartment3 = new Apartment();
        apartment3.setNo(3L);
        apartment3.setName("A 3");
        apartment3.setAddress("ADDR 3");

        apartmentRepository.save(Arrays.asList(apartment1, apartment2, apartment3));

        Page<Apartment> pageResult = apartmentService.getAllAndSortByNo(0, 10, "DESC", "no");
        List<Apartment> listResult = pageResult.getContent();

        Assert.assertEquals(apartment3.getNo(), listResult.get(0).getNo());
        Assert.assertEquals(apartment2.getNo(), listResult.get(1).getNo());
        Assert.assertEquals(apartment1.getNo(), listResult.get(2).getNo());
    }
}
