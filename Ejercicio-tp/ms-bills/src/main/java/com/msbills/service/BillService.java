package com.msbills.service;

import com.msbills.models.Bill;
import com.msbills.repositories.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository repository;

    public List<Bill> getAllBill() {
        return repository.findAll();
    }

    public List<Bill> getBillsByID(String id) {
        return repository.findAllByUserId(id);
    }

    public Bill createBill(Bill bill) {
        try {
            return repository.save(bill);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
