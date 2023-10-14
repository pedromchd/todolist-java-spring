package br.com.pedromchd.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.pedromchd.todolist.model.User;
import br.com.pedromchd.todolist.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String route = request.getServletPath();

        if (route.equals("/tasks")) {

            String auth = request.getHeader("Authorization");

            String authEncoded = auth.substring("Basic".length()).trim();

            byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

            String authString = new String(authDecoded);

            String[] credentials = authString.split(":");

            String username = credentials[0];
            String password = credentials[1];

            User user = userRepository.findByUsername(username);
            if (user != null) {
                if (BCrypt.checkpw(password, user.getPassword())) {
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "A senha informada está incorreta");
                }
            } else {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Usuário informado não encontrado");
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }

}
