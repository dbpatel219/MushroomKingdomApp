package com.mk.api

import com.mk.core.MKCharacter
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.*
import grails.converters.*

@Secured(['ROLE_USER'])
class CharactersController {
	static responseFormats = ['json', 'xml']

    def index() {
        [characters: MKCharacter.list(params)]
    }
}
