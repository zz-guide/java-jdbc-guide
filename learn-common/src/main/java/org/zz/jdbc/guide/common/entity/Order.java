package org.zz.jdbc.guide.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private Long id;
    private String sn;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
