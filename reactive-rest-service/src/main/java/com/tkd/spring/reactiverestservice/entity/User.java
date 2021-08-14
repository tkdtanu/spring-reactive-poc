package com.tkd.spring.reactiverestservice.entity;

import com.tkd.spring.reactiverestservice.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("user")
public class User {

    @Id
    private Integer id;
    private String name;
    private int age;
    private Double salary;

    public com.tkd.spring.reactiverestservice.dto.UserDto toUserDto() {
        return com.tkd.spring.reactiverestservice.dto.UserDto
                .builder()
                .id(this.id)
                .name(this.name)
                .age(this.age)
                .salary(this.salary).build();
    }

}
