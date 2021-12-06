package com.rupeal.weareswat.controller;

import com.rupeal.weareswat.domain.ErrorDTO;
import com.rupeal.weareswat.domain.InvoiceDTO;
import com.rupeal.weareswat.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Class with the required endpoints for manipulating the Invoices
 */
@RestController
@Slf4j
public class BahamasClientController {

    @Autowired
    private ClientService clientService;

    @Operation(summary = "Store the new invoice",
            description = "Store the invoice in the external API and the local datasource", tags = "Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Invoice created with success"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @PostMapping("/store-bahamas-client/{invoiceId}")
    public final ResponseEntity<InvoiceDTO> storeClient(
            @PathVariable("invoiceId") Long invoiceId,
            @RequestParam("fiscal_id") Long fiscalId,
            @RequestParam("name") String name,
            @RequestParam("email") String email) {
        log.info("storeClient with invoiceId:{} and fiscal_id:{} and name:{} and email:{}" ,
                invoiceId,
                fiscalId,
                name,
                email);
        InvoiceDTO invoiceDTO = InvoiceDTO.builder()
                .invoiceId(invoiceId)
                .fiscalId(fiscalId)
                .name(name)
                .email(email)
                .build();
        clientService.storeClient(invoiceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceDTO);
    }

    @Operation(summary = "Retrieve the client with his invoice id",
            description = "Retrieve the invoice from the local datasource", tags = "Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the Invoice from the invoiceId",
                    content = @Content(schema = @Schema(implementation = InvoiceDTO.class))),
            @ApiResponse(responseCode = "404", description = "Invoice not found"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @GetMapping("/retrieve-bahamas-client/{invoiceId}")
    private ResponseEntity<InvoiceDTO> retrieveClient(
            @PathVariable("invoiceId") Long invoiceId) {
        log.info("retrieveClient with invoiceId:{}" ,invoiceId);
        InvoiceDTO invoiceDTO = clientService.retrieveClient(invoiceId);
        if(invoiceDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoiceDTO);
    }

}
