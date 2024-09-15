package com.movieflix.auth.services;

import com.movieflix.auth.models.RefreshToken;
import com.movieflix.auth.models.User;
import com.movieflix.auth.repositories.RefreshTokenRepository;
import com.movieflix.auth.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken createRefreshToken(String username) {
       User user =  userRepository.findByEmail(username)
               .orElseThrow(() -> new UsernameNotFoundException("user not found with email -" + username));

      RefreshToken refreshToken = user.getRefreshToken();

      if(refreshToken == null) {
          long refreshTokenValidity = 30 * 1000;
          refreshToken = RefreshToken.builder()
                  .refreshToken(UUID.randomUUID().toString())
                  .expirationTime(Instant.now().plusMillis(refreshTokenValidity))
                  .user(user)
                  .build();

          refreshTokenRepository.save(refreshToken);
      }
      return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken) {
          RefreshToken refToken  = refreshTokenRepository.findByRefreshToken(refreshToken)
                  .orElseThrow(() -> new RuntimeException("Refresh Token not found"));

          if(refToken.getExpirationTime().compareTo(Instant.now()) < 0) {
              refreshTokenRepository.delete(refToken);
              throw new RuntimeException("Refresh Token Expired");
          }
          return refToken;
    }

}
