package org.zz.jdbc.guide.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private Long id;
    private String sn;
    private Long userId;
    private Date createdAt;
    private Date updatedAt;

    private User user;
}
