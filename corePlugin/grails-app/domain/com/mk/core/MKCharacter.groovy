package com.mk.core

class MKCharacter {

    String name
    boolean isGoodGuy
    MKItem item

    static constraints = {
        item nullable: true
    }
}
