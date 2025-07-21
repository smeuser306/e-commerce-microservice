package com.pdt.models;

public record OrdersVm(String username,
                       double totalPrice,
                       String firstName,
                       String lastName,
                       String emailAddress,
                       String addressLine,
                       String country,
                       String state,
                       String cardName,
                       String cardNumber,
                       String expiration,
                       String cvv,
                       int paymentMethod) {
}
