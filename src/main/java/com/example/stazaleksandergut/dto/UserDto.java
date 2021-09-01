package com.example.stazaleksandergut.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class UserDto {
    private String id;
    private String email;

    private String firstName;
    private String lastName;
}
