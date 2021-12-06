package com.rupeal.weareswat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Class responsible for providing a Mock for the external api in this example
 */
@RestController
@Slf4j
public class ExternalAPIMockController {

    @PostMapping("/register")
    public final ResponseEntity<Object> storeClient(
            @RequestParam("invoice") Long invoice,
            @RequestParam("fiscal_id") Long fiscalId,
            @RequestParam("name") Long name,
            @RequestParam("email") Long email) {
        log.info("storeClient with invoiceId={}, fiscal_id={}, name={}, email={} ", invoice, fiscalId, name, email);
        if(555L == invoice){
            throw new RuntimeException("Error in the external API");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
