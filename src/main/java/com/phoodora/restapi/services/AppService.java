package com.phoodora.restapi.services;

import org.springframework.stereotype.Service;
import com.phoodora.restapi.interfaces.IAppService;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.phoodora.restapi.models.Restaurant;
import com.phoodora.restapi.models.Order;
import com.phoodora.restapi.models.Product;
import com.phoodora.restapi.repositories.RestaurantRepository;
import com.phoodora.restapi.repositories.OrderRepository;
import com.phoodora.restapi.repositories.ProductRepository;

import org.json.simple.JSONObject;

@Service
public class AppService implements IAppService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ProductRepository productRepository;
  
    // ORDER METHODS
    @Override
    public Order findOrderById(int id) {
        Optional<Order> result = orderRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Order> findAllUsersOrders(int id) {
        List<Order> usersOrders = new ArrayList<>();
        for (Order a : orderRepository.findAll()) {
            if(a.users_id == id) {
                usersOrders.add(a);
            }
        }
        return usersOrders;
    }

    @Override
    public List<Order> findAllRestaurantOrders(int id) {
        List<Order> restaurantOrders = new ArrayList<>();
        for (Order a : orderRepository.findAll()) {
            if(a.restaurant_id == id) {
                restaurantOrders.add(a);
            }
        }
        return restaurantOrders;
    }

    @Override
    public Order findUsersCurrentOrder(int id) {
        Order result = new Order();
        for (Order a : findAllUsersOrders(id)) {
            boolean deliveryStatus = a.getDelivered();
            if(deliveryStatus == false) {
                result = a;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Order> findRestaurantCurrentOrder(int id) {
        List<Order> result = new ArrayList<>();
        for (Order a : findAllRestaurantOrders(id)) {
            boolean deliveryStatus = a.getDelivered();
            if(deliveryStatus == false) {
                result.add(a);
            }
        }
        return result;
    }

    @Override
    public void insertToOrder(JSONObject p, int users_id) {
                LocalDateTime dateTime = java.time.LocalDateTime.now();
                String time = dateTime.toString();

                int restaurant_id = restaurantRepository.findByName((String) p.get("restaurant_name")).getId();
        
                Order newOrder = new Order(p, time, users_id, restaurant_id);
                orderRepository.save(newOrder);
    }

    @Override
    public void updateOrder(JSONObject updateStatus) {

        String stateToBeModified = (String) updateStatus.get("state");
        int order_id = (Integer) updateStatus.get("order_id");
        Order order = findOrderById(order_id);

            switch(stateToBeModified) {
                case "received":
                    order.setReceived(true);
                    orderRepository.save(order);
                    break;

                case "preparing":
                order.setPreparing(true);
                orderRepository.save(order);
                    break;

                case "waiting":
                order.setWaiting(true);
                orderRepository.save(order);
                    break; 

                case "delivering":
                order.setDelivering(true);
                orderRepository.save(order);
                    break;

                case "delivered":
                order.setDelivered(true);
                orderRepository.save(order);
                    break;
                }
    }

    // RESTAURANT METHODS
    @Override
    public List<Restaurant> findAllRestaurants() {
        return (List<Restaurant>)restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> findAllUsersRestaurants(int id) {
        List<Restaurant> usersRestaurants = new ArrayList<>();
        for (Restaurant a : restaurantRepository.findAll()) {
            if(a.users_id == id) {
                usersRestaurants.add(a);
            }
        }
        return usersRestaurants;
    }

    @Override
    public Restaurant findByIdRestaurant(int id) {
        Optional<Restaurant> result = restaurantRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    @Override
    public Restaurant insertToRestaurant(JSONObject p, int users_id) {
        String name = (String) p.get("name");
        String address = (String) p.get("address");
        String city = (String) p.get("city"); 
        String postal_code = (String) p.get("postal_code"); 
        String image = (String) p.get("image");
        String operating_hours = (String) p.get("operating_hours");
        String price_level = (String) p.get("price_level");
        String type = (String) p.get("type");

        Restaurant newRestaurant = new Restaurant(name, address, city, postal_code, image, operating_hours, price_level, type, users_id);
        return restaurantRepository.save(newRestaurant);
    }

    @Override
    public boolean deleteRestaurant(int id) {
        try {
            restaurantRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // PRODUCT METHODS
    @Override
    public List<Product> findAllRestaurantProducts(int id) {
        List<Product> restaurantProducts = new ArrayList<>();
        for (Product a : productRepository.findAll()) {
            if(a.restaurant_id == id) {
                restaurantProducts.add(a);
            }
        }
        return restaurantProducts;
    }

    @Override
    public Product insertToProduct(JSONObject p) {
        String category = (String) p.get("category");
        String name = (String) p.get("name");
        double price = Double.valueOf((String) p.get("price"));
        String description = (String) p.get("description"); 
        String image = (String) p.get("image");
        int restaurant_id = Integer.valueOf((String) p.get("restaurant_id"));

        Product newProduct = new Product(category, name, price, description, image, restaurant_id);
        return productRepository.save(newProduct);
    }

    @Override
    public boolean deleteProduct(int id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }   
}    
