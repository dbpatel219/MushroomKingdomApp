package com.mk.core

class EnvUtil {

    static def property(name) {
        return System.env[name] ?: System.getProperty(name)
    }
}
