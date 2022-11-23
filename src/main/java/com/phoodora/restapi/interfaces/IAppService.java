package com.phoodora.restapi.interfaces;

import java.util.List;

import com.phoodora.restapi.models.Order;
import com.phoodora.restapi.models.Product;
import com.phoodora.restapi.models.Restaurant;

import org.json.simple.JSONObject;

public interface IAppService {

    // Required order methods
    Order findOrderById(int id);
    List<Order> findAllUsersOrders(int id);
    List<Order> findAllRestaurantOrders(int id);
    Order findUsersCurrentOrder(int id);
    List<Order> findRestaurantCurrentOrder(int id);
    void insertToOrder(JSONObject p, int users_id);
    void updateOrder(JSONObject updateStatus);

    // Required restaurant methods
    List<Restaurant> findAllRestaurants();
    List<Restaurant> findAllUsersRestaurants(int id);
    Restaurant findByIdRestaurant(int id);
    Restaurant insertToRestaurant(JSONObject p, int users_id);
    boolean deleteRestaurant(int id);
    
    // Required product methods
    List<Product> findAllRestaurantProducts(int id);
    Product insertToProduct(JSONObject p);
    boolean deleteProduct(int id);
}
