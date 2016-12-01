package com.kllect.auth; /**
 * Created by ejz492 on 11/30/16.
 */
public class JWTHeaderAndPayload {
    JWTHeader jwtHeader;
    JWTPayload jwtPayload;

    public JWTHeaderAndPayload(JWTHeader jwtHeader, JWTPayload jwtPayload){
        this.jwtHeader = jwtHeader;
        this.jwtPayload = jwtPayload;
    }
}
