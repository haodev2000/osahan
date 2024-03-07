package com.example.osahaneat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.osahaneat.entity.OrderDetail;
import com.example.osahaneat.entity.Orders;
import com.example.osahaneat.entity.Restaurant;
import com.example.osahaneat.entity.Users;
import com.example.osahaneat.entity.key.KeyOrderDetail;
import com.example.osahaneat.payload.request.OrderRequest;
import com.example.osahaneat.responsitory.OrderDetailResponsitory;
import com.example.osahaneat.responsitory.OrderResponsitory;
import com.example.osahaneat.service.impl.OrderServiceImpl;

@Service

public class OrderService implements OrderServiceImpl {

	@Autowired
	OrderResponsitory orderResponsitory;

	@Autowired
	OrderDetailResponsitory orderDetailResponsitory;

	@Override
	public boolean addOrder(OrderRequest orderRequest) {

		try {
			Users user = new Users();
			user.setId(orderRequest.getUser_id());

			Restaurant restaurant = new Restaurant();
			restaurant.setId(orderRequest.getRes_id());

			Orders orders = new Orders();

			orders.setUsers(user);
			orders.setRestaurants(restaurant);
			
			

			// lưu trước mới lấy ra dc cái id của orders , xong mới set id cho table
			// orderDetail dc
			orderResponsitory.save(orders);
			System.out.println(orders.getUsers() + "+++++++++++++++");
			List<OrderDetail> listItem = new ArrayList<>();

			for (Long idFood : orderRequest.getFood_id()) {

				OrderDetail orderDetail = new OrderDetail();
				KeyOrderDetail keys = new KeyOrderDetail(orders.getId(), idFood);

				orderDetail.setKeys(keys);

				listItem.add(orderDetail);
			}

			orderDetailResponsitory.saveAll(listItem);

			return true;
		} catch (Exception e) {
			System.out.println("Error insert Order" + e);
			
			return false;
		}

	}

}
