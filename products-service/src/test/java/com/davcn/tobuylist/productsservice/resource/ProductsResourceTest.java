package com.davcn.tobuylist.productsservice.resource;

import com.davcn.tobuylist.productsservice.config.AppConfiguration;
import com.davcn.tobuylist.productsservice.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = AppConfiguration.class)
public class ProductsResourceTest {

    private MockMvc mockMvc;

    private String ENDPOINT_API = "/api/products";

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    public ProductsResource productsResource;

    private Product p1 = new Product(0, "p1", 123, "john");

    @Before
    public void setUp() throws Exception {
        //productsResource.setRestTemplate(restTemplate);
        mockMvc = MockMvcBuilders.standaloneSetup(productsResource).build();
    }

    @Test
    public void testGetProducts() throws Exception {

        when(restTemplate
                .exchange(any(String.class),
                            any(HttpMethod.class),
                            any(),
                            any(ParameterizedTypeReference.class)
                )).thenReturn(Arrays.asList(p1));

        mockMvc
                .perform(get(ENDPOINT_API))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id").value(p1.getId()))
                .andExpect(jsonPath("$.[0].name").value(p1.getName()))
                .andExpect(jsonPath("$.[0].price").value(p1.getPrice()))
                .andExpect(jsonPath("$.[0].addedBy").value(p1.getAddedBy()));
    }

    @Test
    public void testCreate() throws Exception {

        ResponseEntity<List<Product>> response = new ResponseEntity<List<Product>>(new ArrayList<Product>());
        when(restTemplate
                .exchange(any(String.class),
                        any(HttpMethod.class),
                        any(),
                        any(ParameterizedTypeReference.class)
                )).thenReturn(new ResponseEntity<new HttpMethod.POST);
        mockMvc
                .perform(post(ENDPOINT_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"p1\", \"price\": 123, \"addedBy\": \"me\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value(p1.getName()))
                .andExpect(jsonPath("$.[0].price").value(p1.getPrice()))
                .andExpect(jsonPath("$.[0].addedBy").value(p1.getAddedBy()));
    }

    public void testSearchShouldBeNull() throws Exception {

        mockMvc
                .perform(get(ENDPOINT_API).param("q", "test"))
                .andExpect(status().isOk());
    }


}
