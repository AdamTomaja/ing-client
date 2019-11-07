package com.cydercode.ing.client.checklogin;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class CheckLoginRequest {

    private String login;

}
