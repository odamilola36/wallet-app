package com.lomari.walletapp.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

@Entity
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper=false)
@Builder
public class User extends BaseClass implements UserDetails {

    @Email(message = "Email should be a valid email")
    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @NotNull(message = "Firstname can not be null")
    private String firstName;

    @NotNull(message = "Lastname can not be null")
    private String lastName;

    private String password;

    @OneToOne
    private Role role;

    @Column(nullable = false, unique = true, name = "username")
    private String username;

    @Past(message = "Date of birth should be in the past")
    private Date dateOfBirth;

    @OneToOne
    private Wallet wallet;

    @Builder.Default
    private boolean isAccountNonLocked = false;

    @Column(nullable = false, unique = true)
    private String passwordSalt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        this.role.getRole().getAccessLevels().forEach(access -> authorities
                .add(new SimpleGrantedAuthority(access.getAccess())));
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.getRole().name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
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
