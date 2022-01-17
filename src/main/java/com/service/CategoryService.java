package com.service;

import com.model.Category;
import com.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    CategoryRepo repository;

    @Override
    public List<Category> getAll(){
        return (List<Category>) repository.findAll();
    }
}
