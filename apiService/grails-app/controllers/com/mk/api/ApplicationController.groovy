package com.mk.api

import grails.core.GrailsApplication

class ApplicationController {

    GrailsApplication grailsApplication

    def index() {
        if (params.sleep) {
            System.sleep(1000)
        }
        [grailsApplication: grailsApplication]
    }
}
