package com.pdt.services;

import com.pdt.discount.grpc.CouponModel;
import com.pdt.discount.grpc.CreateDiscountRequest;
import com.pdt.discount.grpc.DiscountProtoServiceGrpc;
import com.pdt.discount.grpc.GetDiscountRequest;
import com.pdt.entities.Coupon;
import com.pdt.mapper.CouponMapper;
import com.pdt.repositories.DiscountRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class DiscountService extends DiscountProtoServiceGrpc.DiscountProtoServiceImplBase {
    private final DiscountRepository discountRepository;
    private final CouponMapper couponMapper;

    public DiscountService(DiscountRepository discountRepository, CouponMapper couponMapper) {
        this.discountRepository = discountRepository;
        this.couponMapper = couponMapper;
    }

    @Override
    public void getDiscount(GetDiscountRequest request, StreamObserver<CouponModel> responseObserver) {
        Coupon coupon = discountRepository.getDiscount(request.getProductName());
        if (coupon == null) {
            responseObserver.onError(new Exception("Product not found"));
        }
        CouponModel couponModel = couponMapper.toCouponModel(coupon);
        responseObserver.onNext(couponModel);
        responseObserver.onCompleted();
    }

    @Override
    public void createDiscount(CreateDiscountRequest request, StreamObserver<CouponModel> responseObserver) {
        Coupon coupon = couponMapper.toCoupon(request.getCoupon());
        boolean savedCoupon = discountRepository.createDiscount(coupon);
        if (savedCoupon){
            responseObserver.onNext(couponMapper.toCouponModel(coupon));
            responseObserver.onCompleted();
        }
        responseObserver.onError(new Exception("failed to create coupon"));
    }
}
