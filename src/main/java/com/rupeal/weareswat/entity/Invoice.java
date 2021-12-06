package com.rupeal.weareswat.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity to store the Invoices stored on the local datasource
 */
@Data
@Builder
@Entity
@Table(name="INVOICE")
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    private Long invoiceId;
    private Long fiscalId;
    private String name;
    private String email;
}
