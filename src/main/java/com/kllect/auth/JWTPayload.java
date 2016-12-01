package com.kllect.auth; /**
 * Created by ejz492 on 11/29/16.
 */
public class JWTPayload {
    String iss;
    String name;
    String picture;
    String aud;
    String auth_time;
    String user_id;
    String sub;
    String iat;
    String exp;
    String email;
    String email_verified;
    Firebase firebase;
}
