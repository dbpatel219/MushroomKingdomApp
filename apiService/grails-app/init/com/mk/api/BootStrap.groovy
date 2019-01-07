package com.mk.api

import com.mk.core.MKUser

class BootStrap {

    def init = { servletContext ->
        def userCount = MKUser.count()
        if (!userCount) {
            def user = new MKUser()
            user.username = 'mario'
            user.password = 'plumbingainteasy'
            user.email = 'mario@mk.com'
            user.save(flush: true)
        }
    }

    def destroy = {
    }
}
