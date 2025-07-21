package com.pdt.entities;

import java.math.BigDecimal;

public record BasketCheckout(String username,
                             double totalPrice,
                             String firstName,
                             String lastName,
                             String emailAddress,
                             String addressLine,
                             String country,
                             String zipCode,
                             String cardNumber,
                             String cardName,
                             String expiration,
                             String cvv,
                             int paymentMethod) {
}

