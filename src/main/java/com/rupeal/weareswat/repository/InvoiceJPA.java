package com.rupeal.weareswat.repository;

import com.rupeal.weareswat.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Instance of a repository to manipulate the Invoice entities
 */
public interface InvoiceJPA extends JpaRepository<Invoice, Long> {
}
