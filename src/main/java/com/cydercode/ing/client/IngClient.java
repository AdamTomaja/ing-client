package com.cydercode.ing.client;

import com.cydercode.ing.client.checklogin.CheckLoginRequest;
import com.cydercode.ing.client.checklogin.CheckLoginResponse;
import com.cydercode.ing.client.common.IngRequest;
import com.cydercode.ing.client.common.Services;
import com.cydercode.ing.client.history.HistoryRequest;
import com.cydercode.ing.client.history.HistoryResponse;
import com.cydercode.ing.client.history.HistoryResponseData;
import com.cydercode.ing.client.login.LoginRequest;
import com.cydercode.ing.client.login.LoginResponse;
import com.cydercode.ing.client.login.LoginResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Slf4j
public class IngClient {

    private final Configuration configuration;
    private final PasswordEncoder passwordEncoder = new PasswordEncoder();
    private List<String> lastHeaders;

    public IngClient(Configuration configuration) {
        this.configuration = configuration;
    }

    public LoginResponse getToken() {
        CheckLoginRequest checkLoginRequest = CheckLoginRequest.builder().login(configuration.getLogin()).build();
        ResponseEntity<CheckLoginResponse> response = callService(checkLoginRequest, Services.RENCHECKLOGIN, CheckLoginResponse.class);
        String passwordHash = passwordEncoder.createPwdHash(configuration.getPassword(), response.getBody().getData());

        LoginRequest loginRequest = LoginRequest.builder()
                .di("D")
                .login(configuration.getLogin())
                .pwdhash(passwordHash)
                .build();

        ResponseEntity<LoginResponse> loginResponse = callService(loginRequest, Services.RENLOGIN, LoginResponse.class);
        log.info("Login response: {}", loginResponse);
        return loginResponse.getBody();
    }

    public HistoryResponse getHistory(LoginResponseData loginResponseData, HistoryRequest historyRequest) {
        return callService(historyRequest, Services.RENGETFURY, HistoryResponse.class, loginResponseData.getToken())
                .getBody();
    }

    private <T> ResponseEntity<T> callService(Object data, String serviceName, Class<T> responseClass) {
        return callService(data, serviceName, responseClass, "");
    }

    private <T> ResponseEntity<T> callService(Object data, String serviceName, Class<T> responseClass, String token) {
        IngRequest ingRequest = IngRequest.builder()
                .token(token)
                .trace("")
                .data(data)
                .locale("PL")
                .build();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("X-Wolf-Protection", String.valueOf((Math.random())));
        if(lastHeaders != null) {
            httpHeaders.addAll(HttpHeaders.COOKIE, lastHeaders);
        }

        HttpEntity<IngRequest> requestEntity = new HttpEntity<>(ingRequest, httpHeaders);
        ResponseEntity<T> response = restTemplate.exchange(createUrl(serviceName), HttpMethod.POST, requestEntity, responseClass);
        lastHeaders = response.getHeaders().get(HttpHeaders.SET_COOKIE);
        return response;
    }


    private String createUrl(String serviceName) {
        return String.format("%s/%s", configuration.getUrl(), serviceName);
    }
}
