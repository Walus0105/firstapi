package com.project1.firstapi.User;

import com.project1.firstapi.Role.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class User {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @Getter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @Getter
    @Column(name = "surname", nullable = false)
    private String surname;

    @Setter
    @Getter
    @Column(name = "email", nullable = false)
    private String email;

    @Setter
    @Getter
    @Column(name = "contact_number", nullable = false)
    private String contact_number;

    @Setter
    @Getter
    @Column(name = "password", nullable = false)
    private String password = "default_password";

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))


    private Collection<Role> roles;


    public Collection<Role> getRoles() { return roles; }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public User(String name, String surname, String email, String contact_number, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.contact_number = contact_number;
        this.password = password;
    }

}

