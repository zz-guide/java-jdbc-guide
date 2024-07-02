package org.zz.jdbc.guide.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private Long id;
    private String sn;
    private String name;
    private Integer age;
    private String username;
    private String password;
    private String salt;
    private String mobile;
    private Integer gender;
    private String grade;
    private Date birthday; // date类型
    private LocalDateTime createdAt; // datetime类型
    private LocalDateTime updatedAt; // datetime类型
}
