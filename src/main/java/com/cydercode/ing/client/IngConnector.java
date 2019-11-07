package com.cydercode.ing.client;

import com.cydercode.ing.client.accounts.AccountsResponse;
import com.cydercode.ing.client.checklogin.CheckLoginRequest;
import com.cydercode.ing.client.checklogin.CheckLoginResponse;
import com.cydercode.ing.client.common.IngException;
import com.cydercode.ing.client.common.IngRequest;
import com.cydercode.ing.client.common.IngResponse;
import com.cydercode.ing.client.common.ServiceNames;
import com.cydercode.ing.client.history.HistoryRequest;
import com.cydercode.ing.client.history.HistoryResponse;
import com.cydercode.ing.client.login.LoginRequest;
import com.cydercode.ing.client.login.LoginResponse;
import com.cydercode.ing.client.login.LoginResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Slf4j
public class IngConnector {

    private static final String X_WOLF_PROTECTION_HEADER = "X-Wolf-Protection";
    private static final String LOCALE = "PL";
    private static final String TRACE = "";
    private static final String DI = "D";

    private final Configuration configuration;
    private final PasswordEncoder passwordEncoder = new PasswordEncoder();
    private List<String> cookies;

    public IngConnector(Configuration configuration) {
        this.configuration = configuration;
    }

    public LoginResponse login() throws IngException {
        CheckLoginRequest checkLoginRequest = CheckLoginRequest.builder().login(configuration.getLogin()).build();
        ResponseEntity<CheckLoginResponse> response = callService(checkLoginRequest, ServiceNames.RENCHECKLOGIN, CheckLoginResponse.class);
        return callService(createLoginRequest(response), ServiceNames.RENLOGIN, LoginResponse.class).getBody();
    }

    private LoginRequest createLoginRequest(ResponseEntity<CheckLoginResponse> response) {
        return LoginRequest.builder()
                    .di(DI)
                    .login(configuration.getLogin())
                    .pwdhash(passwordEncoder.createPwdHash(configuration.getPassword(), response.getBody().getData()))
                    .build();
    }

    public AccountsResponse getAccounts(LoginResponseData loginResponseData) throws IngException {
        return callService(null, ServiceNames.RENGETALLINGPRDS, AccountsResponse.class, loginResponseData.getToken()).getBody();
    }

    public HistoryResponse getHistory(LoginResponseData loginResponseData, HistoryRequest historyRequest) throws IngException {
        return callService(historyRequest, ServiceNames.RENGETFURY, HistoryResponse.class, loginResponseData.getToken())
                .getBody();
    }

    private <T extends IngResponse> ResponseEntity<T> callService(Object data, String serviceName, Class<T> responseClass) throws IngException {
        return callService(data, serviceName, responseClass, "");
    }

    private <T extends IngResponse> ResponseEntity<T> callService(Object data, String serviceName, Class<T> responseClass, String token) throws IngException {
        RestTemplate restTemplate = new RestTemplate();
        IngRequest ingRequest = createRequest(data, token);
        HttpHeaders httpHeaders = createHeaders();
        HttpEntity<IngRequest> requestEntity = new HttpEntity<>(ingRequest, httpHeaders);
        log.info("Calling service: {} with request: {}", serviceName, requestEntity);
        ResponseEntity<T> response = restTemplate.exchange(createUrl(serviceName), HttpMethod.POST, requestEntity, responseClass);
        log.info("Response: {}", response);
        List<String> cookies = response.getHeaders().get(HttpHeaders.SET_COOKIE);
        if(cookies != null) {
            this.cookies = cookies;
        }

        if("ERROR".equals(response.getBody().getStatus())) {
            throw new IngException(response.getBody());
        }

        return response;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(X_WOLF_PROTECTION_HEADER, String.valueOf((Math.random())));
        if(cookies != null) {
            httpHeaders.addAll(HttpHeaders.COOKIE, cookies);
        }

        return httpHeaders;
    }

    private IngRequest createRequest(Object data, String token) {
        return IngRequest.builder()
                    .token(token)
                    .trace(TRACE)
                    .data(data)
                    .locale(LOCALE)
                    .build();
    }

    private String createUrl(String serviceName) {
        return String.format("%s/%s", configuration.getUrl(), serviceName);
    }
}
