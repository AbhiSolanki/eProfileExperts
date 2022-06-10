package com.eProfile.eProfileExperts.dto;


import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDetailsDto {
    @NotNull
    private String service;
    @NotNull
    private Date servicedate;
    @NotNull
    private String name;
    @NotNull
    private String phone;
    private String email;
    @NotNull
    private String address;
    private String briefServiceDes;
}
