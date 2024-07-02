package brennan.demo.jwt.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue
    int id;
    @Column(nullable = false)
    String username;
    String password;
    String firstName;
    String lastName;
    String country;
    @Enumerated(EnumType.STRING)
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
        //Representa la autoridad otorgada al ususario autenticado.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //Pongo en true ya que el propio token tiene una fecha de caducidad
                     //Por lo tanto, se va a trabajar ese tema en el JWT Service y no acá.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //Mismo que arriba
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //Mismo que arriba
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
