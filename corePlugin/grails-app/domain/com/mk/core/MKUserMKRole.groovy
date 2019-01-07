package com.mk.core

import grails.compiler.GrailsCompileStatic
import grails.gorm.DetachedCriteria
import groovy.transform.ToString
import org.codehaus.groovy.util.HashCodeHelper

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class MKUserMKRole implements Serializable {

	private static final long serialVersionUID = 1

	MKUser mkUser
	MKRole mkRole

	@Override
	boolean equals(other) {
		if (other instanceof MKUserMKRole) {
			other.mkUserId == mkUser?.id && other.mkRoleId == mkRole?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (mkUser) {
            hashCode = HashCodeHelper.updateHash(hashCode, mkUser.id)
		}
		if (mkRole) {
		    hashCode = HashCodeHelper.updateHash(hashCode, mkRole.id)
		}
		hashCode
	}

	static MKUserMKRole get(long mkUserId, long mkRoleId) {
		criteriaFor(mkUserId, mkRoleId).get()
	}

	static boolean exists(long mkUserId, long mkRoleId) {
		criteriaFor(mkUserId, mkRoleId).count()
	}

	private static DetachedCriteria criteriaFor(long mkUserId, long mkRoleId) {
		MKUserMKRole.where {
			mkUser == MKUser.load(mkUserId) &&
			mkRole == MKRole.load(mkRoleId)
		}
	}

	static MKUserMKRole create(MKUser mkUser, MKRole mkRole, boolean flush = false) {
		def instance = new MKUserMKRole(mkUser: mkUser, mkRole: mkRole)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(MKUser u, MKRole r) {
		if (u != null && r != null) {
			MKUserMKRole.where { mkUser == u && mkRole == r }.deleteAll()
		}
	}

	static int removeAll(MKUser u) {
		u == null ? 0 : MKUserMKRole.where { mkUser == u }.deleteAll() as int
	}

	static int removeAll(MKRole r) {
		r == null ? 0 : MKUserMKRole.where { mkRole == r }.deleteAll() as int
	}

	static constraints = {
		mkRole validator: { MKRole r, MKUserMKRole ur ->
			if (ur.mkUser?.id) {
				MKUserMKRole.withNewSession {
					if (MKUserMKRole.exists(ur.mkUser.id, r.id)) {
						return ['userRole.exists']
					}
				}
			}
		}
	}

	static mapping = {
		id composite: ['mkUser', 'mkRole']
		version false
	}
}
