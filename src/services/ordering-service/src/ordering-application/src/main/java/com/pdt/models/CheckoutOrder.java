package com.pdt.models;

public record CheckoutOrder(String username, double totalPrice, String firstName, String lastName, String emailAddress,
                            String addressLine, String country, String state, String zipCode, String cardName,
                            String cardNumber, String cardExpiration, String cvv, int paymentMethod) {
}
