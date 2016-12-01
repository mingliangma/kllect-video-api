package com.kllect.auth

import grails.test.mixin.TestFor
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(VerifyService)
class VerifyServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {

        when:
            JWTPayload payload = service.verifyToken("eyJhbGciOiJSUzI1NiIsImtpZCI6ImEyNjFmYWM1NTFiZGEyYzFiN2YzNjkyMDk5MDllNWQ5ZjA2MzMyZGEifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20va2xsZWN0dmlkZW8iLCJuYW1lIjoiTWluZ2xpYW5nIE1hIiwicGljdHVyZSI6Imh0dHBzOi8vc2NvbnRlbnQueHguZmJjZG4ubmV0L3YvdDEuMC0xL3AxMDB4MTAwLzEyMTE1OTk3XzEwMjA1MDc3MzExOTU1NjkzXzg4MjYxNzIwMDQxMjQxNjk1OF9uLmpwZz9vaD0wMDFmMGM4Y2VhYjc5ZjFiY2JiOGUwZTc1YzU4YTA3NSZvZT01OEZCNENCQiIsImF1ZCI6ImtsbGVjdHZpZGVvIiwiYXV0aF90aW1lIjoxNDgwMzAxMzQ5LCJ1c2VyX2lkIjoiWGgyVGRnTXZtZFZaS3N6QzhoSjRkOTEybWpMMiIsInN1YiI6IlhoMlRkZ012bWRWWktzekM4aEo0ZDkxMm1qTDIiLCJpYXQiOjE0ODA1NjUzMjQsImV4cCI6MTQ4MDU2ODkyNCwiZW1haWwiOiJtaW5nbGlhbmcubWFAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImZhY2Vib29rLmNvbSI6WyIxMDIwNzYwODcyMzU1OTQwMSJdLCJlbWFpbCI6WyJtaW5nbGlhbmcubWFAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZmFjZWJvb2suY29tIn19.JoYOuy6uT8neyoCgs9ao_N8GQCDyvPbATXH-lnwRTX2kYR6UG4owfFj_scWQ4qNjXnMQeqKTA0A_4maDG5brnUMicbsCWfCk_qAcVPc4j-REg-6WU2YukCvZBmEwT0lVC8lxiA_dQbv5wQvQlLcm5dp0vieCzgEr811QhloudSS3wzOfdcfq_7RAgShqvPd_IFi6nBCVixeXMsHivLCUOND6LxMwijuwyCl__moIuwednJNs-Lazeb4hkV9wyJApgyJyAi1oBd5a4ZWSNp9P2pVoGEN0gt3fuQgeJO-_amW1oboXYqfl0stSlNMej_VIqRy3o9OfrRZIW-UztJ-DsQ")

        then:
            payload != null
            payload.iss == "https://securetoken.google.com/kllectvideo"
            payload.name == "Mingliang Ma"
            payload.aud == "kllectvideo"
    }
}
