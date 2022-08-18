package com.chaeking.api.util.resttemplate;

import com.chaeking.api.config.vault.BookSearchConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class NaverApiRestTemplate extends RestTemplate {
    static String apiUrl;
    static String clientId;

    public <T> ResponseEntity<T> get(String url, HttpHeaders httpHeaders, Class<T> clazz) {
        return this.exchange(url, HttpMethod.GET, null, httpHeaders, clazz);
    }

    public <T> ResponseEntity<T> post(String url, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
        return this.exchange(url, HttpMethod.POST, body, httpHeaders, clazz);
    }

    public <T> ResponseEntity<T> put(String url, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
        return this.exchange(url, HttpMethod.PUT, body, httpHeaders, clazz);
    }

    public <T> ResponseEntity<T> delete(String url, HttpHeaders httpHeaders, Class<T> clazz) {
        return this.delete(url, httpHeaders, null, clazz);
    }

    public <T> ResponseEntity<T> delete(String url, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
        return this.exchange(url, HttpMethod.DELETE, body, httpHeaders, clazz);
    }

    private <T> ResponseEntity<T> exchange(String url, HttpMethod method, Object body, HttpHeaders headers, Class<T> clazz) {
        if(headers == null) headers = new HttpHeaders();
        headers.set("Accept", "application/json; charset=utf-8");
        headers.set("Content-Type", "application/json; charset=utf-8");
        headers.set("X-Naver-Client-Id", BookSearchConfig.Naver.getClientId());
        headers.set("X-Naver-Client-Secret", BookSearchConfig.Naver.getClientSecret());
        return this.exchange(url, method, new HttpEntity<>(body, headers), clazz);
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, @Nullable HttpEntity<?> entity, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return super.exchange(apiUrl + url, method, entity, responseType, uriVariables);
    }

}
