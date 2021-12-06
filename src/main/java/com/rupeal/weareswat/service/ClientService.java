package com.rupeal.weareswat.service;

import com.rupeal.weareswat.domain.InvoiceDTO;
import com.rupeal.weareswat.entity.Invoice;
import com.rupeal.weareswat.repository.InvoiceJPA;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service responsible to execute the operations with the invoices
 */
@Slf4j
@AllArgsConstructor
@Service
public class ClientService {

    private final ExternalApiService externalApiService;
    private final InvoiceJPA invoiceJPA;

    public void storeClient(InvoiceDTO invoiceDTO) {
        externalApiService.registerInvoice(invoiceDTO);
        Invoice invoice = invoiceFromDTO(invoiceDTO);
        invoiceJPA.save(invoice);
    }

    public InvoiceDTO retrieveClient(Long clientId) {
        Optional<Invoice> optInvoice = invoiceJPA.findById(clientId);
        if (!optInvoice.isPresent()){
            return  null;
        }
        return DTOFromInvoice(optInvoice.get());
    }

    private Invoice invoiceFromDTO(InvoiceDTO invoiceDTO) {
        return Invoice.builder()
                .invoiceId(invoiceDTO.getInvoiceId())
                .fiscalId(invoiceDTO.getFiscalId())
                .name(invoiceDTO.getName())
                .email(invoiceDTO.getEmail())
                .build();
    }

    private InvoiceDTO DTOFromInvoice(Invoice invoice) {
        return InvoiceDTO.builder()
                .invoiceId(invoice.getInvoiceId())
                .fiscalId(invoice.getFiscalId())
                .name(invoice.getName())
                .email(invoice.getEmail())
                .build();
    }

}
