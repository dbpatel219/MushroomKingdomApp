package com.mk.core

class PropertyUtil {
    static def get(String name) {
        return System.env[name] ?: System.getProperty(name)
    }
}
