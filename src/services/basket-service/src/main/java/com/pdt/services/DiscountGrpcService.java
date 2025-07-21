package com.pdt.services;

import com.pdt.basket.grpc.CouponModel;
import com.pdt.basket.grpc.DiscountProtoServiceGrpc;
import com.pdt.basket.grpc.GetDiscountRequest;
import org.springframework.stereotype.Service;

@Service
public class DiscountGrpcService {
    private final DiscountProtoServiceGrpc.DiscountProtoServiceBlockingStub discountProto;

    public DiscountGrpcService(DiscountProtoServiceGrpc.DiscountProtoServiceBlockingStub discountProto) {
        this.discountProto = discountProto;
    }

    public CouponModel getDiscount(String productName){
        GetDiscountRequest request = GetDiscountRequest.newBuilder().setProductName(productName).build();
        return discountProto.getDiscount(request);
    }
}
