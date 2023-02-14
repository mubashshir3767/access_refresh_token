package com.example.refresh_access_token.receive;

import com.example.refresh_access_token.entity.PermissionEnum;
import com.example.refresh_access_token.entity.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserRegisterDto {
    private String name;
    private String username;
    private String password;

    @JsonProperty("roles")
    private List<RoleEnum> perRoleEnumList;
    @JsonProperty("permissions")
    private List<PermissionEnum> permissionEnumList;

}
