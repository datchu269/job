package com.example.fixmemoryleak.service;

import com.example.fixmemoryleak.entity.Product;
import com.example.fixmemoryleak.repo.ProductDao;
import com.example.fixmemoryleak.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao dao;
    private final ProductRepository repository;

    @Transactional
    public List<Long> saveSampleProducts() {

        List<Product> sampleProducts = Arrays.asList(
                new Product("Product 1", 100.0, "Description for Product 1"),
                new Product("Product 2", 200.0, "Description for Product 2"),
                new Product("Product 3", 300.0, "Description for Product 3")
        );
        repository.findById(1000L);
        List<Product> res =  repository.saveAll(sampleProducts);
        System.err.println(res.get(0).getId());
        dao.save(sampleProducts);
        return dao.saveAll(sampleProducts);
    }
}
