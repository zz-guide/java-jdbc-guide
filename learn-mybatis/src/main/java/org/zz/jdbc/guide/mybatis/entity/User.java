package org.zz.jdbc.guide.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Date createdAt;
    private Date updatedAt;

    // 扩展字段
    private int extId;

    private List<Order> orders;
}
