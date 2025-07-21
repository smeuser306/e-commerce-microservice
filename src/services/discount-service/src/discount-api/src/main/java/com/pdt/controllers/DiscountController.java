package com.pdt.controllers;

import com.pdt.entities.Coupon;
import com.pdt.repositories.DiscountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {
    private final DiscountRepository discountRepository;

    public DiscountController(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @GetMapping
    public ResponseEntity<Coupon> GetDiscount(String productName)
    {
        var coupon = discountRepository.getDiscount(productName);
        return ok(coupon);
    }

    @PostMapping
    public ResponseEntity<Coupon> createDiscount(@RequestBody Coupon coupon)
    {
        discountRepository.createDiscount(coupon);
        return ResponseEntity.created(URI.create("api/v1/catalog/get/" + coupon.getProductName())).build();
    }

    @PutMapping
    public ResponseEntity<Boolean> updateDiscount(@RequestBody Coupon coupon)
    {
        return ok(discountRepository.updateDiscount(coupon));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteDiscount(String productName)
    {
        return ok(discountRepository.deleteDiscount(productName));
    }
}
