package com.example.refresh_access_token.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
@Document
public class UserEntity implements UserDetails {
    private String name;
    private String username;
    private String password;
    private List<RoleEnum> perRoleEnumList;

    private List<PermissionEnum> permissionEnumList;

    public UserEntity(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public UserEntity(String name, String username, String password, List<RoleEnum> perRoleEnumList, List<PermissionEnum> permissionEnumList) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.perRoleEnumList = perRoleEnumList;
        this.permissionEnumList = permissionEnumList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//       List<SimpleGrantedAuthority> roles = new ArrayList<>();
//
//       permissionEnumList.forEach((role)->{
//           roles.add(new SimpleGrantedAuthority("ROLE_"+role));
//       });
//
//       perRoleEnumList.forEach((perRole)->{
//           roles.add(new SimpleGrantedAuthority(perRole.name()));
//       });

       return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
