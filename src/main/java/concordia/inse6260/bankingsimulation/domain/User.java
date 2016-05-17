package concordia.inse6260.bankingsimulation.domain;

import concordia.inse6260.bankingsimulation.domain.enums.Gender;
import concordia.inse6260.bankingsimulation.domain.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Ruixiang on 10/15/2015.
 */

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private Gender gender;
    private LocalDate birthDate;
    private String email;
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CheckingAccount checkingAccount;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CreditAccount creditAccount;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private SavingAccount savingAccount;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public CreditAccount getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(CreditAccount creditAccount) {
        this.creditAccount = creditAccount;
    }

    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == Role.USER)
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        else return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
