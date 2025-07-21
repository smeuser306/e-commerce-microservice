package com.pdt.repositories;

import com.pdt.entities.Coupon;
import org.springframework.stereotype.Repository;

public interface DiscountRepository {
    Coupon getDiscount(String productName);
    boolean createDiscount(Coupon coupon);
    boolean updateDiscount(Coupon coupon);
    boolean deleteDiscount(String productName);
}
