package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    String find_all() throws JsonProcessingException;
}
