package com.rupeal.weareswat.domain;

import lombok.*;

/**
 * Intermediary POJO for transporting the information between the services
 */
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private Long invoiceId;
    private Long fiscalId;
    private String name;
    private String email;
}
