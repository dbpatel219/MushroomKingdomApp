package com.mk.core

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class MKUser implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String email
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    void setEmail(String _email) {
        email = _email?.toLowerCase()
    }

    void setUsername(String _username) {
        username = _username?.toLowerCase()
    }

    Set<MKRole> getAuthorities() {
        (MKUserMKRole.findAllByMkUser(this) as List<MKUserMKRole>)*.mkRole as Set<MKRole>
    }

    Boolean isAdmin() {
        def authorities = getAuthorities()*.authority
        return authorities.contains('ROLE_ADMIN')
    }

    static constraints = {
        password blank: false, password: true
        username blank: false, unique: true
    }

    static mapping = {
        password column: '`password`'
    }
}
