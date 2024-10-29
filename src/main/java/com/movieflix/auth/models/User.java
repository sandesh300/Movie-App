    package com.movieflix.auth.models;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.util.Collection;
    import java.util.List;

    @Entity
    @Table(name = "users")
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public class User implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer userId;

        @NotBlank(message = "The name field can't be blank")
        private String name;

        @NotBlank(message = "The username field can't be blank")
        @Column(unique = true)
        private String username;

        @NotBlank(message = "The email field can't be blank")
        @Column(unique = true)
        @Email(message = "Please enter email in proper format!")
        private String email;

        @NotBlank(message = "The password field can't be blank")
        @Size(min = 5, message = "The password must have at least 5 characters")
        private String password;

        @OneToOne(mappedBy = "user")
        private RefreshToken refreshToken;

        @OneToOne(mappedBy = "user")
        private ForgotPassword forgotPassword;

        @Enumerated(EnumType.STRING)
        private UserRole role;


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

        @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
        private boolean isAccountNonExpired = true;

        @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
        private boolean isAccountNonLocked = true;

        @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
        private boolean isCredentialsNonExpired = true;

        @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
        private boolean isEnabled = true;

    }