package com.msbills.repositories;

import com.msbills.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, String> {
    List<Bill> findAllBycustomerBill(String userId);
}
