package com.davcn.tobuylist.dbservice.repository;

import com.davcn.tobuylist.dbservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
