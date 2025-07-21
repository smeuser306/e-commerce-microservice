package com.pdt.mapper;

import com.pdt.discount.grpc.CouponModel;
import com.pdt.entities.Coupon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    CouponModel toCouponModel(Coupon coupon);

    Coupon toCoupon(CouponModel coupon);
}
