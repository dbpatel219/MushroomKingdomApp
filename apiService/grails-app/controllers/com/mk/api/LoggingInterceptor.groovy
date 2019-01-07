package com.mk.api

import org.slf4j.MDC


class LoggingInterceptor {

    def springSecurityService

    LoggingInterceptor() {
        matchAll()
    }

    boolean before() {
        MDC.put('userId', springSecurityService?.currentUser?.id as String ?: '-1')
        true
    }

    boolean after() { true }

    void afterView() {
        MDC.remove('userId')
    }
}
