package com.burtsev.rest_test.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    @Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 2, max = 20, message = "Name to short (2) or long (30)")
    private String firstName;
    @NotEmpty(message = "The field cannot be empty")
    @Size(min = 2, max = 20, message = "Name to short (2) or long (30)")
    private String lastName;
    @Min(value = 0, message = "Age must be greater than 0" )
    private int age;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NotEmpty
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }

//        public void setRoles(String[] roles) {
//        Set<Role> roleSet = new HashSet<>();
//        for (String role : roles) {
//            if (role != null) {
//                if (role.equals("ROLE_ADMIN")) {
//                    roleSet.add(new Role(2, role));
//                }
//                if (role.equals("ROLE_USER")) {
//                    roleSet.add(new Role(1, role));
//                }
//            }
//        }
//        this.roles = roleSet;
//    }
    public String getRolesToString() {
        String s = getRoles().toString().replaceAll("^\\[|\\]$", "");
        return s.replace("ROLE_", "");
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  roles;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                age == user.age &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, email, firstName, lastName, age, roles);
    }
}
