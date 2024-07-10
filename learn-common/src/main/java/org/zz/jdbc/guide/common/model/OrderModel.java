package org.zz.jdbc.guide.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {
    private Long id;
    private String sn;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 外键实体
    private UserModel user;
}
