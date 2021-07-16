package hu.procats.invoicemanager;

import hu.procats.invoicemanager.dtos.UserDTO;
import hu.procats.invoicemanager.jpamodels.User;
import hu.procats.invoicemanager.models.AuthenticationRequest;
import hu.procats.invoicemanager.models.AuthenticationResponse;
import hu.procats.invoicemanager.services.MyUserDetailsService;
import hu.procats.invoicemanager.util.ErrorInfo;
import hu.procats.invoicemanager.util.FrontendException;
import hu.procats.invoicemanager.util.Handler;
import hu.procats.invoicemanager.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SystemResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping({ "/ping" })
    public String ping()
    {
        return "Ping.";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e)
        {
            throw new FrontendException("A felhasználónév vagy jelszó helytelen.");
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User register(@RequestBody UserDTO userDTO) throws Exception {

        try {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(userDTO.getUsername());

            throw new FrontendException("A felhasználónév foglalt.");
        } catch (UsernameNotFoundException e)
        {
            // Ha nincs ilyen username, akkor regelünk
            User user = new User();
            user.setUserName(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setActive(true);
            user.setRoles("ROLE_USER");
            userRepository.save(user);
            return user;
        }
    }
}
