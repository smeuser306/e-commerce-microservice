package com.pdt.configs;

import com.pdt.annotations.ApplicationService;
import com.pdt.configurations.EmailSettings;
import jakarta.mail.MessagingException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(
        basePackages = "com.pdt.annotations",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {ApplicationService.class})}
)
public class InfrastructureConfigs {
    private final EmailSettings emailSettings;

    public InfrastructureConfigs(EmailSettings emailSettings) {
        this.emailSettings = emailSettings;
    }

    @Bean
    public JavaMailSender javaMailSender() throws MessagingException {
        var sender = new JavaMailSenderImpl();
        sender.setHost(emailSettings.getHost());
        sender.setPassword(emailSettings.getPassword());
        sender.setUsername(emailSettings.getFrom());
        sender.setPort(emailSettings.getPort());
//        sender.testConnection();
        return sender;
    }
}

/*
  },
          "GoogleEmailSettings": {
          "From": "amalitech.welfare@gmail.com",
          "Password": "lyag lyaw ohpn oqes",
          "Host": "smtp.gmail.com",
          "DisplayName": "Amalitech Welfare",
          "Port": 587
          },
*/
