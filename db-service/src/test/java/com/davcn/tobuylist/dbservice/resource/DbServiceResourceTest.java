package com.davcn.tobuylist.dbservice.resource;

import com.davcn.tobuylist.dbservice.model.Product;
import com.davcn.tobuylist.dbservice.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DbServiceResourceTest {

    private MockMvc mockMvc;

    private String ENDPOINT_API = "/api/products";

    @Mock
    public ProductRepository productRepository;

    @InjectMocks
    public DbServiceResource dbServiceResource;

    private Product p1 = new Product("p1", 123, "john");
    private Product p2 = new Product("p2", 456, "doe");

    @Before
    public void setUp() throws Exception {
        dbServiceResource.setProductRepository(productRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(dbServiceResource).build();
    }

    @Test
    public void testGetAll() throws Exception {

        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        mockMvc
                .perform(get(ENDPOINT_API))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllWithData() throws Exception {

        when(productRepository.findAll()).thenReturn(Arrays.asList(p1,p2));
        mockMvc
                .perform(get(ENDPOINT_API))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].name").value(p1.getName()))
                .andExpect(jsonPath("$.[0].price").value(p1.getPrice()))
                .andExpect(jsonPath("$.[0].addedBy").value(p1.getAddedBy()))
                .andExpect(jsonPath("$.[1].id").isNumber())
                .andExpect(jsonPath("$.[1].name").value(p2.getName()))
                .andExpect(jsonPath("$.[1].price").value(p2.getPrice()))
                .andExpect(jsonPath("$.[1].addedBy").value(p2.getAddedBy()));
    }

    @Test
    public void testPost() throws Exception {

        when(productRepository.save(any(Product.class))).thenReturn(null);
        mockMvc
                .perform(post(ENDPOINT_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"p1\", \"price\": 111, \"addedBy\": \"me\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAll() throws Exception {

        doNothing().when(productRepository).deleteAll();
        mockMvc
                .perform(delete(ENDPOINT_API))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProduct() throws Exception {

        doNothing().when(productRepository).delete(1L);
        mockMvc
                .perform(delete(ENDPOINT_API + "/1"))
                .andExpect(status().isOk());
    }
}
