package com.example.demo.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Collection;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class User implements UserDetails {

   //@Id
  // @GeneratedValue
  // private int id;

/*
    @NotNull(message = "Must not be null")
    @NotBlank(message = "Must not be blank")
    @NotEmpty(message = "Must not be Empty")
    private String name;

    @NotNull(message = "Must not be null")
    @Email(message = "Invalid email")
    @NotEmpty(message = "Must not be empty")
    private String email;

    @NotNull(message = "Must not be null")
    @NotBlank(message = "Must not be blank")
    @NotEmpty(message = "Must not be empty")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    public boolean isAuthentic(User user) {
        return email.equals(user.getEmail()) && password.equals(user.getPassword());
    }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
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

    public boolean alReadyExist(User user) {
        return user.email.equals(user.getEmail()) && password.equals(user.getPassword());
    }

 */
}

