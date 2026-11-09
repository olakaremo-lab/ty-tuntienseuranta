package com.olak.tyotuntiseuranta;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@SpringBootTest 
@AutoConfigureMockMvc 
public class WebLayerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test 
    public void TestEditPage() throws Exception{
        this.mockMvc.perform(get("/work/edit/1"))
            .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print());
    }

    @Test 
    public void TestMainPage() throws Exception{
        this.mockMvc.perform(get("/work"))
            .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print());
    }

    @Test 
    public void TestApi() throws Exception{
       this.mockMvc.perform(get("/api/work"))
        .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print());  
    }   
}
