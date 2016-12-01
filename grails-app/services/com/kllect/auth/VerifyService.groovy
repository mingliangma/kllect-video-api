package com.kllect.auth

import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.JWTVerifyException
import com.auth0.jwt.internal.org.apache.commons.codec.Decoder
import com.auth0.jwt.internal.org.apache.commons.codec.DecoderException
import com.auth0.jwt.internal.org.apache.commons.lang3.Validate
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.grails.web.json.JSONObject

import java.nio.charset.StandardCharsets
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.PublicKey
import java.security.SignatureException
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate


class VerifyService {
    static final String FIREBASE_CERTIFICATE_URL = 'https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com'


    static void main(String[] args){

        try {
            VerifyService v = new VerifyService()
            v.verifyToken("_eyJhbGciOiJSUzI1NiIsImtpZCI6ImEyNjFmYWM1NTFiZGEyYzFiN2YzNjkyMDk5MDllNWQ5ZjA2MzMyZGEifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20va2xsZWN0dmlkZW8iLCJuYW1lIjoiTWluZ2xpYW5nIE1hIiwicGljdHVyZSI6Imh0dHBzOi8vc2NvbnRlbnQueHguZmJjZG4ubmV0L3YvdDEuMC0xL3AxMDB4MTAwLzEyMTE1OTk3XzEwMjA1MDc3MzExOTU1NjkzXzg4MjYxNzIwMDQxMjQxNjk1OF9uLmpwZz9vaD0wMDFmMGM4Y2VhYjc5ZjFiY2JiOGUwZTc1YzU4YTA3NSZvZT01OEZCNENCQiIsImF1ZCI6ImtsbGVjdHZpZGVvIiwiYXV0aF90aW1lIjoxNDgwMzAxMzQ5LCJ1c2VyX2lkIjoiWGgyVGRnTXZtZFZaS3N6QzhoSjRkOTEybWpMMiIsInN1YiI6IlhoMlRkZ012bWRWWktzekM4aEo0ZDkxMm1qTDIiLCJpYXQiOjE0ODA1NjUzMjQsImV4cCI6MTQ4MDU2ODkyNCwiZW1haWwiOiJtaW5nbGlhbmcubWFAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImZhY2Vib29rLmNvbSI6WyIxMDIwNzYwODcyMzU1OTQwMSJdLCJlbWFpbCI6WyJtaW5nbGlhbmcubWFAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZmFjZWJvb2suY29tIn19.JoYOuy6uT8neyoCgs9ao_N8GQCDyvPbATXH-lnwRTX2kYR6UG4owfFj_scWQ4qNjXnMQeqKTA0A_4maDG5brnUMicbsCWfCk_qAcVPc4j-REg-6WU2YukCvZBmEwT0lVC8lxiA_dQbv5wQvQlLcm5dp0vieCzgEr811QhloudSS3wzOfdcfq_7RAgShqvPd_IFi6nBCVixeXMsHivLCUOND6LxMwijuwyCl__moIuwednJNs-Lazeb4hkV9wyJApgyJyAi1oBd5a4ZWSNp9P2pVoGEN0gt3fuQgeJO-_amW1oboXYqfl0stSlNMej_VIqRy3o9OfrRZIW-UztJ-DsQ")
        } catch (IllegalStateException e) {
            println "IllegalStateException:::"+e.message
        } catch (Exception e) {
            println e.message
        }
    }


    JWTPayload verifyToken(String token) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, IOException, SignatureException, JWTVerifyException{
        JWTHeaderAndPayload jwtHeaderAndPayload = decode(token);
        String certificate = getCertificate(jwtHeaderAndPayload.jwtHeader.kid);
        return verifyToken(token, certificate);
    }

    JWTPayload verifyToken(String token, String certificate) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, IOException, SignatureException, JWTVerifyException {
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        InputStream stream = new ByteArrayInputStream(certificate.getBytes(StandardCharsets.UTF_8));
        X509Certificate cert = (X509Certificate)certFactory.generateCertificate(stream);
        PublicKey publicKey = cert.getPublicKey();
        JWTVerifier verifier = new JWTVerifier(publicKey);
        Map<String, Object> claims = verifier.verify(token)


        Gson gson = new Gson()
        String claimsResult = gson.toJson(claims);
        JWTPayload payload = gson.fromJson(claimsResult, JWTPayload.class)
        return payload;
    }

    private String getCertificate(String kid) throws Exception {
        String json = readUrl(FIREBASE_CERTIFICATE_URL);
        JSONObject certJson = new JSONObject(json);
        return certJson.getString(kid);
    }

    private String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }

    }

    private String decodeAndParse(String b64String) throws IOException, DecoderException {
        Decoder decoder = new com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64(true);
        Validate.notNull(b64String);
        return new String((byte[]) decoder.decode(b64String), "UTF-8");
    }

    private JWTHeader decodeAndParseHeader(String b64String) throws IOException, DecoderException {
        Gson gson = new Gson();
        return gson.fromJson(decodeAndParse(b64String), JWTHeader.class);
    }

    private JWTPayload decodeAndParsePayload(String b64String) throws IOException, DecoderException {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(decodeAndParse(b64String));
        removeDotComAtKeys(jsonElement);
        return new Gson().fromJson(jsonElement, JWTPayload.class);
    }

    private void removeDotComAtKeys(JsonElement jsonElement){
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonObject firebase = jsonObject.getAsJsonObject("firebase");
            if (firebase != null){
                JsonObject identities = firebase.getAsJsonObject("identities");
                Set<Map.Entry<String, JsonElement>> entrySet = new HashSet<>(identities.entrySet()); // Make a copy so we can modify JsonObject

                for (Map.Entry<String, JsonElement> entry : entrySet) {
                    String key = entry.getKey();
                    JsonElement value = entry.getValue();

                    if (key.endsWith(".com")) {
                        String newKey = key.replaceFirst(".com", "");
                        identities.add(newKey, value);
                        identities.remove(key);
                    }
                }
            }
        }
    }

    private JWTHeaderAndPayload decode(String token) throws IOException, DecoderException {
        if(token != null && !"".equals(token)) {
            String[] pieces = token.split("\\.");
            if(pieces.length != 3) {
                throw new IllegalStateException("Wrong number of segments: " + pieces.length);
            } else {
                JWTHeader jwtHeader = decodeAndParseHeader(pieces[0]);
                JWTPayload jwtPayload = decodeAndParsePayload(pieces[1]);
                return new JWTHeaderAndPayload(jwtHeader, jwtPayload);
            }
        } else {
            throw new IllegalStateException("token not set");
        }
    }
}
