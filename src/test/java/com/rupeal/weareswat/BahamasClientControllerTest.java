package com.rupeal.weareswat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rupeal.weareswat.controller.BahamasClientController;
import com.rupeal.weareswat.domain.InvoiceDTO;
import com.rupeal.weareswat.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class BahamasClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private BahamasClientController bahamasClientController;

    ObjectMapper objectMapper = new ObjectMapper();

    private final String PARAM_FISCAL_ID = "fiscal_id";
    private final String PARAM_NAME = "name";
    private final String PARAM_EMAIL = "email";


    private final Long INVOICE_ID = 1234L;
    private final Long FISCAL_ID = 4321L;
    private final String NAME = "Nome Test";
    private final String EMAIL = "teste@com";


    private final InvoiceDTO invoiceDTO = InvoiceDTO.builder()
            .invoiceId(INVOICE_ID)
            .fiscalId(FISCAL_ID)
            .name(NAME)
            .email(EMAIL)
            .build();

    String invoiceJson = objectMapper.writeValueAsString(invoiceDTO);

    public BahamasClientControllerTest() throws JsonProcessingException {
    }


    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(bahamasClientController).build();
    }

    @Test
    public void storeClient_success() throws Exception {

        doNothing().when(clientService).storeClient(any(InvoiceDTO.class));
        mockMvc.perform(get("/store-bahamas-client/" + INVOICE_ID)
                        .param(PARAM_FISCAL_ID, FISCAL_ID.toString())
                        .param(PARAM_NAME, NAME.toString())
                        .param(PARAM_EMAIL, EMAIL.toString())
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(invoiceJson));
    }

    @Test
    public void retrieveClient_success() throws Exception {
        doReturn(invoiceDTO).when(clientService).retrieveClient(eq(INVOICE_ID));
        mockMvc.perform(get("/retrieve-bahamas-client/" + INVOICE_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(invoiceJson));
    }

    @Test
    public void retrieveClient_empty() throws Exception {
        doReturn(null).when(clientService).retrieveClient(eq(INVOICE_ID));
        mockMvc.perform(get("/retrieve-bahamas-client/" + INVOICE_ID))
                .andExpect(status().isNotFound());
    }

}
