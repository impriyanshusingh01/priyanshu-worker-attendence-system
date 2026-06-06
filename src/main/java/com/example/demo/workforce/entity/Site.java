
package com.example.demo.workforce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "sites",
        indexes = {
                @Index(name = "idx_site_name", columnList = "siteName")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String siteName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Boolean active = true;
}
