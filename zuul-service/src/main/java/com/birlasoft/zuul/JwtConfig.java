package com.birlasoft.zuul;

import org.springframework.beans.factory.annotation.Value;

public class JwtConfig {
    @Value("${auth.jwt.uri:/users/**}")
    private String Uri;

    @Value("${auth.jwt.header:Authorization}")
    private String header;

    @Value("${auth.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{300000}}")
    private int expiration;

    @Value("${auth.jwt.secret:secret-key}")
    private String secret;

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
