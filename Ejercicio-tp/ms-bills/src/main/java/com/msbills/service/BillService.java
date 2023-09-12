package com.msbills.service;

import com.msbills.models.Bill;
import com.msbills.repositories.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository repository;

    public List<Bill> getAllBill() {
        return repository.findAll();
    }

    public Bill getBillByID(String id) {
        Optional<Bill> bill = repository.findById(id);

        if (bill.isPresent()) {
            return bill.get();
        } else {
            throw new RuntimeException("Bill not found");
        }
    }

    public List<Bill> getBillsByUserID(String id) {
        try {
            List<Bill> bills = repository.findAllBycustomerBill(id);
            return bills;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Bill createBill(Bill bill) {
        try {
            return repository.save(bill);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
