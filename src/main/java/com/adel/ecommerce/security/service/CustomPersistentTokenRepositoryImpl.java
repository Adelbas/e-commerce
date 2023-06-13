package com.adel.ecommerce.security.service;

import java.util.Date;
import java.util.List;

import com.adel.ecommerce.security.model.RememberMeToken;
import com.adel.ecommerce.security.repository.TokenRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomPersistentTokenRepositoryImpl implements PersistentTokenRepository {

    private final TokenRepository rememberMeTokenRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        RememberMeToken rmToken = new RememberMeToken();
        rmToken.setSeries(token.getSeries());
        rmToken.setUsername(token.getUsername());
        rmToken.setTokenValue(token.getTokenValue());
        rmToken.setDate(token.getDate());
        rememberMeTokenRepository.save(rmToken);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        RememberMeToken existingToken = this.rememberMeTokenRepository.findBySeries(series);
        if (existingToken != null) {
            existingToken.setTokenValue(tokenValue);
            existingToken.setDate(lastUsed);
            rememberMeTokenRepository.save(existingToken);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentRememberMeToken persistentRememberMeToken = null;
        RememberMeToken existingToken = this.rememberMeTokenRepository.findBySeries(seriesId);
        if (existingToken != null) {
            persistentRememberMeToken = new PersistentRememberMeToken(existingToken.getUsername(),existingToken.getSeries(), existingToken.getTokenValue(), existingToken.getDate());
        }
        return persistentRememberMeToken;
    }

    @Override
    public void removeUserTokens(String username) {
        List<RememberMeToken> tokens = rememberMeTokenRepository.findByUsername(username);
        rememberMeTokenRepository.deleteAllInBatch(tokens);
    }
}