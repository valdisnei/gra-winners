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
                .andExpect(jsonPath("$.min[0].producer").value("Bo Derek"))
                .andExpect(jsonPath("$.min[0].previousWin").value(1984))
                .andExpect(jsonPath("$.min[0].followingWin").value(1985))

                .andExpect(jsonPath("$.min[1].interval").value(1))
                .andExpect(jsonPath("$.min[1].producer").value("Lorenzo di Bonaventura,Ian Bryce,Tom DeSanto and Don Murphy"))
                .andExpect(jsonPath("$.min[1].previousWin").value(2010))
                .andExpect(jsonPath("$.min[1].followingWin").value(2011))

                .andExpect(jsonPath("$.max[0].interval").value(8))
                .andExpect(jsonPath("$.max[0].producer").value("Lorenzo di Bonaventura,Ian Bryce,Tom DeSanto and Don Murphy"))
                .andExpect(jsonPath("$.max[0].previousWin").value(2015))
                .andExpect(jsonPath("$.max[0].followingWin").value(2023))

                .andExpect(jsonPath("$.max[1].interval").value(2))
                .andExpect(jsonPath("$.max[1].producer").value("Joel Silver"))
                .andExpect(jsonPath("$.max[1].previousWin").value(1989))
                .andExpect(jsonPath("$.max[1].followingWin").value(1991));

    }

}
