package com.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4, message = "User name must be at least 4 characters!!")
    private String name;
    @Email(message = "Email address is not valid !!")
    private String email;
    @NotEmpty
    @Size(min = 6, max = 15, message = "Password must be between 6 and 15 characters !!")
    private String password;
    @NotEmpty
    private String about;
}


