package com.eProfile.eProfileExperts.dto;


import com.sun.istack.internal.NotNull;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
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
