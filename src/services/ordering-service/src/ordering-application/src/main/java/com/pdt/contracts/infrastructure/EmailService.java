package com.pdt.contracts.infrastructure;

import com.pdt.models.Email;

public interface EmailService {
    boolean sendEmail(Email email);
}
