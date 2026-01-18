package com.stocksync.backend.model;

import jakarta.persistence.*;
import lombok.*;

// 3 imports for spring security...

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity //tells sb that this is a db table ...not a java class
@Table(name = "users") // users plural standard ....name of the db table
@Data// annotation for lombok to fill boring getter setter 50 lines code automatically
@NoArgsConstructor // FOR JPA TO WORK
@AllArgsConstructor// for builder to work ..
@Builder
public class User implements UserDetails// wearing security mas k..
 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    // makes the column like null is false and  uniquue should be true ...for unique not null usernames //
    @Column(nullable = false, unique = true)
    private String username ;

    @Column(nullable = false)// pass cant be null
    private String password;

    @Column(nullable = false) //email cannot be false
    private String email;

    @Enumerated(EnumType.STRING) /* The Problem: By default, Java saves Enums as numbers in the database (CUSTOMER = 0, ADMIN = 1).

The Risk: If later you add a new role GUEST at the top of the list, GUEST becomes 0, and suddenly all your old CUSTOMERs (who were 0) are now GUESTs!

The Fix: EnumType.STRING tells Java: "Don't save the number '0'. Save the actual word 'CUSTOMER'."

Memory Trick: "Always store Strings, not secret codes."*/

    @Column(nullable = false)
    private Role role;
 //spring security mandatory methods....

// 2. Tell Spring what "Role" this user has (ADMIN or CUSTOMER)
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
} /*Spring, please take my simple Enum string ("ADMIN") and wrap
 it in a fancy format (SimpleGrantedAuthority) that your
 security guards understand."*/

// 3. Boilerplate: Account is always active/unlocked for this simple project
@Override
public boolean isAccountNonExpired() { return true; }

@Override
public boolean isAccountNonLocked() { return true; }

@Override
public boolean isCredentialsNonExpired() { return true; }

@Override
public boolean isEnabled() { return true; }
}
