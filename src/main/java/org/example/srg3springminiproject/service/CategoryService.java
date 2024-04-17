package org.example.srg3springminiproject.service;

import org.apache.ibatis.javassist.NotFoundException;
import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.request.CategoryRequest;
import org.example.srg3springminiproject.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {


    Category updateCategory(Integer id, CategoryRequest categoryRequest);

    Category removeCategory(Integer id);
}
