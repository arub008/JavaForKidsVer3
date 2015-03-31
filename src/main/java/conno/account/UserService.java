package conno.account;

import java.util.Calendar;
import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.thymeleaf.context.WebContext;

@Scope("session")
public class UserService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    public Account curAccount;


    /*	@PostConstruct

    protected void initialize() {
            accountRepository.save(new Account("user", "demo", "ROLE_USER"));
            accountRepository.save(new Account("admin", "admin", "ROLE_ADMIN"));
        }
        */
    @PostConstruct
    protected void initialize() {
        creatAccountIfNotExist(new Account("user", "demo", "ROLE_USER"));
        creatAccountIfNotExist(new Account("admin", "admin", "ROLE_ADMIN"));
    }

    private void creatAccountIfNotExist(Account account) {
        if (accountRepository.findByEmail(account.getEmail()) == null) {
            accountRepository.save(account);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return createUser(account);
    }

   /* public UserDetails printUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("Такого пользователя не существует");
        } else {
            System.out.println("account.getEmail()+account.getId() = " + account.getEmail() + account.getId());
        }
        return createUser(account);
    }
*/

    public void signin(Account account) {
        SecurityContextHolder.getContext().setAuthentication(authenticate(account));
    }

    private Authentication authenticate(Account account) {
        return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));
    }

    private User createUser(Account account) {
        return new User(account.getEmail(), account.getPassword(), Collections.singleton(createAuthority(account)));
    }

    private GrantedAuthority createAuthority(Account account) {
        return new SimpleGrantedAuthority(account.getRole());
    }

}
