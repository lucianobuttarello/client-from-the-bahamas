package com.rupeal.weareswat;


import com.rupeal.weareswat.domain.InvoiceDTO;
import com.rupeal.weareswat.entity.Invoice;
import com.rupeal.weareswat.repository.InvoiceJPA;
import com.rupeal.weareswat.service.ClientService;
import com.rupeal.weareswat.service.ExternalApiService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService service;

    @Mock
    private ExternalApiService externalApiService;

    @Mock
    private InvoiceJPA invoiceJPA;

    private final InvoiceDTO invoiceDTO = InvoiceDTO.builder()
            .invoiceId(1324L)
            .fiscalId(4321L)
            .name("Name")
            .email("email@com")
            .build();

    private final Invoice invoice = Invoice.builder()
            .invoiceId(1324L)
            .fiscalId(4321L)
            .name("Name")
            .email("email@com")
            .build();

    @Test
    public void storeCLient_success() {
        doNothing().when(externalApiService).registerInvoice(eq(invoiceDTO));
        doReturn(null).when(invoiceJPA).save(eq(invoice));
        service.storeClient(invoiceDTO);
        verify(externalApiService, times(1)).registerInvoice(eq(invoiceDTO));
        verify(invoiceJPA, times(1)).save(eq(invoice));
    }


    @Test
    public void retrieveCLient_success() {
        doReturn(Optional.of(invoice)).when(invoiceJPA).findById(eq(invoice.getInvoiceId()));
        InvoiceDTO retInvoiceDTO = service.retrieveClient(this.invoiceDTO.getInvoiceId());
        Assert.assertEquals(invoiceDTO, retInvoiceDTO);
    }

    @Test
    public void retrieveCLient_empty() {
        doReturn(Optional.ofNullable(null)).when(invoiceJPA).findById(eq(invoice.getInvoiceId()));
        InvoiceDTO retInvoiceDTO = service.retrieveClient(this.invoiceDTO.getInvoiceId());
        Assert.assertNull(retInvoiceDTO);
    }

}
