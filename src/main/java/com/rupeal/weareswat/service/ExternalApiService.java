package com.rupeal.weareswat.service;

import com.rupeal.weareswat.domain.InvoiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service responsible for the external communicate with the external API
 */
@Slf4j
@Service
public class ExternalApiService {

    @Value("${url_client_api}")
    private String URL_CLIENT_API;

    private final String CLIENT_ENDPOINT = "/register";

    private final String QUERY_INVOICE = "invoice=";
    private final String QUERY_FISCAL = "fiscal_id=";
    private final String QUERY_NAME = "name=";
    private final String QUERY_EMAIL = "email=";

    private RestTemplate restTemplate = new RestTemplate();

    public void registerInvoice(InvoiceDTO invoiceDTO) {
        log.info("registerClient : {}", invoiceDTO);
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(URL_CLIENT_API)
                .path(CLIENT_ENDPOINT)
                .query(QUERY_INVOICE + invoiceDTO.getInvoiceId())
                .query(QUERY_FISCAL + invoiceDTO.getFiscalId())
                .query(QUERY_NAME + invoiceDTO.getName())
                .query(QUERY_EMAIL + invoiceDTO.getEmail())
                .build();
        restTemplate.exchange(uri.encode().toString(), HttpMethod.POST, null, Object.class);
    }
}

