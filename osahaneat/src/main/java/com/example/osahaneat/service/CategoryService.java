package com.example.osahaneat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.osahaneat.dto.CategoryDTO;
import com.example.osahaneat.dto.FoodDTO;
import com.example.osahaneat.entity.Category;
import com.example.osahaneat.entity.Food;
import com.example.osahaneat.responsitory.CategoryResponsitory;
import com.example.osahaneat.service.impl.CategoryServiceImpl;

@Service
public class CategoryService implements CategoryServiceImpl {

	@Autowired
	CategoryResponsitory categoryResponsitory;

	@Override
	public List<CategoryDTO> getListCategory() {

		Page<Category> listCategories = categoryResponsitory.findAll(PageRequest.of(0, 3, Sort.by("id")));

		List<CategoryDTO> listDTO = new ArrayList<>();

		for (Category category : listCategories) {

			CategoryDTO categoryDTO = new CategoryDTO();

			categoryDTO.setNameCategory(category.getNameCategory());

			List<FoodDTO> listFoodDTOs = new ArrayList<>();

			for (Food data : category.getListFood()) {

				FoodDTO foodDTO = new FoodDTO();

				foodDTO.setTitle(data.getTitle());
				foodDTO.set_freeship(data.is_freeship());
				foodDTO.setImage(data.getImage());

				listFoodDTOs.add(foodDTO);
			}

			categoryDTO.setListFood(listFoodDTOs);

			listDTO.add(categoryDTO);

		}

		return listDTO;
	}

	@Override
	public boolean update(Long id, String name) {
		boolean isSuccess = false;

		Category category = categoryResponsitory.getById(id);

		if (category != null) {
			category.setNameCategory(name);

			categoryResponsitory.save(category);

			isSuccess = true;
		}

		return isSuccess;
	}

	@Override
	public boolean add(String name) {
		boolean isSuccess = false;

		try {
			Category category = new Category();

			category.setNameCategory(name);

			categoryResponsitory.save(category);

			isSuccess = true;

		} catch (Exception e) {
			System.out.println("Error add category : " + e);
		}

		return isSuccess;

	}

}
