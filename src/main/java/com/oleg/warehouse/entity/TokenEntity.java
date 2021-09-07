package com.oleg.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "token")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntity {

    private final static int EXPIRATION_MINUTES = 60;
    @Column(name = "create_at")
    @Builder.Default
    private LocalTime createAt = LocalTime.now();
    @Column(name = "expire_at")
    @Builder.Default
    private LocalTime expireAt = LocalTime.now().plusMinutes(EXPIRATION_MINUTES);
    @Id
    @Builder.Default
    private String token = UUID.randomUUID().toString();

}
