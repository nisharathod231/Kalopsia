package com.kalopsia.backend.controller;

import com.kalopsia.backend.request.OrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public void placeOrder(@RequestBody OrderRequest orderRequest) {
        System.out.println(orderRequest);
        logger.info("ORDER_PLACED");
        List<Integer> items = orderRequest.getItems();
        System.out.println(items);
        for (int item : items) {
            logger.info("ITEM_ORDERED : {}", item);
        }
    }
}
