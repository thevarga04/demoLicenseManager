package demo.licensemanager.partner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@EnabledIfSystemProperty(named = "profile", matches = "SIT")
class PartnerRestTest {

  @Autowired
  MockMvc mockMvc;


  @Test
  void getPartnerList() throws Exception {
    // When
    mockMvc.perform(MockMvcRequestBuilders.get("/api/some"))
      .andDo(MockMvcResultHandlers.print())
      .andExpect(MockMvcResultMatchers.header().exists("CorrelationId"));
  }


}