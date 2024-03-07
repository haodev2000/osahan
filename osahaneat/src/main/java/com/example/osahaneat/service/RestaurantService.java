package com.example.osahaneat.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.osahaneat.dto.CategoryDTO;
import com.example.osahaneat.dto.FoodDTO;
import com.example.osahaneat.dto.RestaurantDTO;
import com.example.osahaneat.entity.Category;
import com.example.osahaneat.entity.Food;
import com.example.osahaneat.entity.MenuRestaurant;
import com.example.osahaneat.entity.RatingRestaurant;
import com.example.osahaneat.entity.Restaurant;
import com.example.osahaneat.responsitory.RestaurantResponsitory;
import com.example.osahaneat.service.impl.RestaurantServiceImpl;

@Service
public class RestaurantService implements RestaurantServiceImpl {

	@Autowired
	RestaurantResponsitory responsitory;

	@Autowired
	FilesStorageServiceImpl filesStorageServiceImpl;

	@Override
	public boolean insertRestaurant(String title, String subtitle, String description, boolean isfreship,
			String address, String openDate, MultipartFile file) {

		boolean isSuccess = false;
		try {
			boolean isSuccessSaveFile = filesStorageServiceImpl.save(file);

			if (isSuccessSaveFile) {
				Restaurant restaurant = new Restaurant();

				restaurant.setTitle(title);
				restaurant.setSubtitle(subtitle);
				restaurant.setDescription(description);
				restaurant.setImage(file.getOriginalFilename());
				restaurant.setIsfreship(isfreship);
				restaurant.setAddress(address);

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
				Date opendate;
				opendate = (Date) simpleDateFormat.parse(openDate);
				restaurant.setOpenDate(opendate);

				responsitory.save(restaurant);

				isSuccess = true;
			}
		} catch (ParseException e) {
			System.out.println("Error Insert Restaurant " + e.getMessage());
			e.printStackTrace();
		}

		return isSuccess;
	}

	@Override
	public List<RestaurantDTO> getListRestaurant() {

		Page<Restaurant> listPage = responsitory.findAll(PageRequest.of(0, 6));

		List<RestaurantDTO> listDTO = new ArrayList<RestaurantDTO>();

		for (Restaurant restaurant : listPage) {

			RestaurantDTO restaurantDTO = new RestaurantDTO();
			
			restaurantDTO.setId(restaurant.getId());
			restaurantDTO.setTitle(restaurant.getTitle());
			restaurantDTO.setImage(restaurant.getImage());
			restaurantDTO.setSubtitle(restaurant.getSubtitle());
			restaurantDTO.setIsfreship(restaurant.isIsfreship());
			restaurantDTO.setRate(rateRestaurant(restaurant.getListRatingRestaurants()));
			
			
			listDTO.add(restaurantDTO);

		}
		

		return listDTO;
	}

	private double rateRestaurant(List<RatingRestaurant> listRating) {

		double total = 0;

		for (RatingRestaurant data : listRating) {
			total += data.getRatePoint();
		}

		return total / listRating.size();
	}

	@Override
	public RestaurantDTO getDetail(Long id) {

		Optional<Restaurant> restaurant = responsitory.findById(id);

		RestaurantDTO rDto = new RestaurantDTO();

		if (restaurant.isPresent()) {

			Restaurant data = restaurant.get();
			
			rDto.setId(data.getId());
			rDto.setTitle(data.getTitle());
			rDto.setImage(data.getImage());
			rDto.setSubtitle(data.getSubtitle());
			rDto.setRate(rateRestaurant(data.getListRatingRestaurants()));
			rDto.setIsfreship(data.isIsfreship());
			rDto.setOpenDate(data.getOpenDate());
			rDto.setDescription(data.getDescription());
			
			

			List<CategoryDTO> listCategoryDTOs = new ArrayList<CategoryDTO>();

			for (MenuRestaurant menuRestaurant : data.getListMenuRestaurants()) {

				CategoryDTO categoryDTO = new CategoryDTO();

				categoryDTO.setNameCategory(menuRestaurant.getCategory().getNameCategory());

				// adđ list food
				List<FoodDTO> listFoodDTO = new ArrayList<>();
				for (Food food : menuRestaurant.getCategory().getListFood()) {

					FoodDTO foodDTO = new FoodDTO();

					foodDTO.setId(food.getId());
					foodDTO.setImage(food.getImage());
					foodDTO.set_freeship(food.is_freeship());
					foodDTO.setTitle(food.getTitle());
					foodDTO.setDescription(food.getDescription());
					foodDTO.setPrice(food.getPrice());

					listFoodDTO.add(foodDTO);
				}

				categoryDTO.setListFood(listFoodDTO);
				
				listCategoryDTOs.add(categoryDTO);

			}

			rDto.setLisCategoryDTOs(listCategoryDTOs);
		}

		return rDto;
	}

	@Override
	public boolean update(Long id, String title, String subtitle, String description, boolean isfreship, String address,
			String openDate, MultipartFile file) {
		
		boolean isSuccess = false;
		try {
			boolean isSuccessSaveFile = filesStorageServiceImpl.save(file);

			if (isSuccessSaveFile) {
				Restaurant restaurant = responsitory.getById(id);

				restaurant.setTitle(title);
				restaurant.setSubtitle(subtitle);
				restaurant.setDescription(description);
				restaurant.setImage(file.getOriginalFilename());
				restaurant.setIsfreship(isfreship);
				restaurant.setAddress(address);

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date opendate;
				opendate = (Date) simpleDateFormat.parse(openDate);
				restaurant.setOpenDate(opendate);
				

				responsitory.save(restaurant);

				isSuccess = true;
			}
		} catch (ParseException e) {
			System.out.println("Error update Restaurant " + e.getMessage());
			e.printStackTrace();
		}

		return isSuccess;
	}

}
