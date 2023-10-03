package com.texo.it.grawinners;


import com.texo.it.grawinners.infrastructure.controller.MinMaxController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@WebAppConfiguration
class MinMaxControllerIntegrationTest {
    private MockMvc mockMvc;

    @Autowired
    private MinMaxController minMaxController;

    @Autowired
    protected WebApplicationContext wac;

    @Test
    public void getWinnersMinMaxTest() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.minMaxController).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/producers/intervals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.min[0].interval").value(1))
                .andExpect(jsonPath("$.min[0].producer").value("Joel Silver"))
                .andExpect(jsonPath("$.min[0].previousWin").value(1990))
                .andExpect(jsonPath("$.min[0].followingWin").value(1991))

                .andExpect(jsonPath("$.max[0].interval").value(6))
                .andExpect(jsonPath("$.max[0].producer").value("Bo Derek"))
                .andExpect(jsonPath("$.max[0].previousWin").value(1984))
                .andExpect(jsonPath("$.max[0].followingWin").value(1990))

                .andExpect(jsonPath("$.max[1].interval").value(9))
                .andExpect(jsonPath("$.max[1].producer").value("Buzz Feitshans"))
                .andExpect(jsonPath("$.max[1].previousWin").value(1985))
                .andExpect(jsonPath("$.max[1].followingWin").value(1994));


    }

}
