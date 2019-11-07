package com.cydercode.ing.client;

import com.cydercode.ing.client.checklogin.CheckLoginResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

@Slf4j
public class PasswordEncoder {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    public String createPwdHash(String password, CheckLoginResponseData loginData) {
        String maskAndSalt = mixSaltAndMaskData(password, loginData.getMask(), loginData.getSalt());
        log.info("Masked and salted: {}", maskAndSalt);
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, loginData.getKey()).hmacHex(maskAndSalt);
    }

    private String mixSaltAndMaskData(String secret, String mask, String salt) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < mask.length(); i += 1) {
            if ('+' == mask.charAt(i)) {
                result.append(salt.charAt(i));
            } else if ('*' == mask.charAt(i)) {
                result.append(secret.charAt(i));
            }
        }

        return result.toString();
    }
}
