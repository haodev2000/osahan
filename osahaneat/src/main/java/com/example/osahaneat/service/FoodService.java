package com.example.osahaneat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.osahaneat.dto.FoodDTO;
import com.example.osahaneat.entity.Category;
import com.example.osahaneat.entity.Food;
import com.example.osahaneat.responsitory.FoodResponsitory;
import com.example.osahaneat.service.impl.FoodServiceImpl;

@Service
public class FoodService implements FoodServiceImpl {

	@Autowired
	FoodResponsitory foodResponsitory;

	@Autowired
	FilesStorageServiceImpl filesStorageServiceImpl;

	@Override
	public boolean insertFood(String title, Double price, String time_ship, boolean is_freeship, Long cateId,
			MultipartFile file) {
		boolean isSuccess = false;

		try {
			boolean isSuccessSaveFile = filesStorageServiceImpl.save(file);

			if (isSuccessSaveFile) {
				Food food = new Food();

				food.setTitle(title);
				food.setImage(file.getOriginalFilename());
				food.setPrice(price);
				food.setTimeShip(time_ship);
				food.set_freeship(is_freeship);

				Category category = new Category();
				category.setId(cateId);

				food.setCategory(category);

				foodResponsitory.save(food);

				isSuccess = true;
			}
		} catch (ParseException e) {
			System.out.println("Error Insert Food " + e.getMessage());
			e.printStackTrace();
		}

		return isSuccess;
	}

	@Override
	public List<FoodDTO> getListFood() {

		Page<Food> listFood = foodResponsitory.findAll(PageRequest.of(0, 6));

		List<FoodDTO> listDTO = new ArrayList<FoodDTO>();

		for (Food food : listFood) {

			FoodDTO foodDTO = new FoodDTO();

			foodDTO.setImage(food.getImage());

			foodDTO.setTitle(food.getTitle());

			foodDTO.set_freeship(food.is_freeship());
			
			foodDTO.setPrice(food.getPrice());
			
			foodDTO.setDescription(food.getDescription());

			listDTO.add(foodDTO);

		}

		return listDTO;
	}
	
	@Override
	public List<FoodDTO> getListFood_Popular() {

		Page<Food> listFood = foodResponsitory.findAll(PageRequest.of(0, 12));

		List<FoodDTO> listDTO = new ArrayList<FoodDTO>();

		for (Food food : listFood) {

			FoodDTO foodDTO = new FoodDTO();
			
			foodDTO.setId(food.getId());

			foodDTO.setImage(food.getImage());

			foodDTO.setTitle(food.getTitle());
			
			foodDTO.setPrice(food.getPrice());
			
			foodDTO.setDescription(food.getDescription());

			foodDTO.set_freeship(food.is_freeship());

			listDTO.add(foodDTO);

		}

		return listDTO;
	}

	@Override
	public boolean updateFood(Long id, String title, Double price, String time_ship, boolean is_freeship, Long cateId,
			MultipartFile file) {
		boolean isSuccess = false;
		
		Food f = foodResponsitory.getById(id);
		
		if(f != null) {
			try {
				boolean isSuccessSaveFile = filesStorageServiceImpl.save(file);

				if (isSuccessSaveFile) {
					

					f.setTitle(title);
					f.setImage(file.getOriginalFilename());
					f.setPrice(price);
					f.setTimeShip(time_ship);
					f.set_freeship(is_freeship);

					Category category = new Category();
					category.setId(cateId);

					f.setCategory(category);

					foodResponsitory.save(f);

					isSuccess = true;
				}
			} catch (ParseException e) {
				System.out.println("Error Update Food " + e.getMessage());
				e.printStackTrace();
			}
		}

		return isSuccess;
	}

}
