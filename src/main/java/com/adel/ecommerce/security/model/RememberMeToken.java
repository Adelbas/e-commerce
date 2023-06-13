package com.adel.ecommerce.security.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "persistent_logins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RememberMeToken {
    @Id
    private String series;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String tokenValue;
    @Column(name = "last_used")
    private Date date;
}
