package com.kllect.auth


import grails.rest.*
import grails.converters.*

class VerifyController {
    def verifyService

    def verifyToken() {
        Map claimsResult = verifyService.verifyToken(params.token)
    }
}
