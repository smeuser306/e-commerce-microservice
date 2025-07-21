package com.pdt.models;

import java.util.UUID;

public record UpdateOrder(UUID id, String username, double totalPrice, String firstName, String lastName, String emailAddress,
                          String addressLine, String country, String state, String zipCode, String cardName,
                          String cardNumber, String cardExpiration, String cvv, int paymentMethod) {
}

