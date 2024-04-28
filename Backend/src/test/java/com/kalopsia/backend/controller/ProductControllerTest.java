package com.kalopsia.backend.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalopsia.backend.entity.Product;
import com.kalopsia.backend.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureMockMvc
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private ProductService productService;

    private List<Product> expectedProducts;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product(1, "Mock-Name-1", 20, 10, "Mock-Image-URL-1", "Mock-Category-1"));
        expectedProducts.add(new Product(2, "Mock-Name-2", 20, 10, "Mock-Image-URL-2", "Mock-Category-2"));
    }

    @Test
    public void shouldReturnAllProductsWhenGetListAPIIsCalled() throws Exception {
        when(productService.getAllProducts()).thenReturn(expectedProducts);

        MvcResult mvcResult = mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();

        List<Product> actualProducts = mapper.readValue(responseBody, new TypeReference<>(){});

        assertNotNull(actualProducts, "Response should contain a list of products");
        assertIterableEquals(expectedProducts, actualProducts, "Retrieved products should match expected products");
    }

    @Test
    public void shouldReturnSpecifiedProductWhenProductIdIsGiven() throws Exception {
        int productId = 0;
        when(productService.getProduct(productId)).thenReturn(expectedProducts.get(productId));

        MvcResult mvcResult = mockMvc.perform(get("/api/products/" + productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        Product actualProduct = mapper.readValue(responseBody, new TypeReference<>(){});

        assertNotNull(actualProduct);
        assertEquals(expectedProducts.get(0), actualProduct);
    }
}
