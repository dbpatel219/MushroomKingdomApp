package com.mk.api

import grails.core.GrailsApplication
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
class VersionController {
	static responseFormats = ['json', 'xml']

    GrailsApplication grailsApplication

    def index() {
        def version = grailsApplication.metadata.getApplicationVersion()
        def major = version?.split('\\.')[0]

        [version: version, major: major]
    }
}
