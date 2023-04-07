package com.example.spring.jwt.mongodb.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class MailControllerTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private MailController mailController;

    private MockMvc mockMvc;

    public MailControllerTest() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(mailController).build();
    }
    @Test
    public void sendEmailTest() throws Exception {
        String to = "test@example.com";
        String subject = "Test Subject";
        String message = "Test Message";
        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        
        Mockito.doNothing().when(javaMailSender).send(mailMessage);
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/mail")
            .param("to", to)
            .param("subject", subject)
            .param("message", message))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
          
        Mockito.verify(javaMailSender, Mockito.times(1)).send(mailMessage);
        
        String responseBody = result.getResponse().getContentAsString();
        assertEquals("Email sent successfully!", responseBody);
    }
}
