package org.example.bnx.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;

    private LocalDateTime created;
    private LocalDateTime expires;
    private Boolean valided;
    @JoinColumn(name = "userid",nullable = false)
    @ManyToOne()
    private User user;
}
