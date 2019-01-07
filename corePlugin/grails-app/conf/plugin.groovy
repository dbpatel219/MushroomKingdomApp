def property(String name) {
    return System.env[name] ?: System.getProperty(name)
}

/** Spring Security Configuration **/
grails.plugin.springsecurity.rest.token.storage.jwt.secret='qrD6h8K6S9503Q06Y6Rfk19TErImPYqa'

grails.plugin.springsecurity.password.algorithm='bcrypt'

grails.plugin.springsecurity.rememberMe.cookieName = 'mk_api'
grails.plugin.springsecurity.rememberMe.key = 'MKAPI'

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.mk.core.MKUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.mk.core.MKUserMKRole'
grails.plugin.springsecurity.authority.className = 'com.mk.core.MKRole'

grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.successHandler.defaultTargetUrl = "/"

grails.plugin.springsecurity.ui.register.emailBody = 'Welcome to MushroomKingdom $user.username!<br><br>  Please activate your registration via $url'
grails.plugin.springsecurity.ui.register.emailFrom = "noreply@mk.com"
grails.plugin.springsecurity.ui.register.emailSubject = 'Activate your MushroomKingdom account'

grails.plugin.springsecurity.ui.forgotPassword.emailBody = 'Hi $user.username,<br><br> Looks like you need some help with your password.  If you didn\'t make this request then ignore the email; no changes have been made.<br><br>  If you did request a password reset, then click $url to reset your password'
grails.plugin.springsecurity.ui.forgotPassword.emailFrom = "noreply@mk.com"
grails.plugin.springsecurity.ui.forgotPassword.emailSubject = 'Reset your MK password'

grails.mail.default.from = "noreply@mk.com"

grails {
    mail {
        host = "email-smtp.us-east-1.amazonaws.com"
        port = 587
        username = property('SMTP_MAIL_UN')
        password = property('SMTP_MAIL_PW')
        props = ["mail.smtp.starttls.enable":"true",
                 "mail.smtp.port":"587"]
    }
}

environments {
    development {
        grails.plugin.fields.disableLookupCache = true

        dataSource {
            if (property('LOCAL_DB_URL') ?: property('DATABASE_URL')) {
                dbCreate = "update"
                driverClassName = "org.postgresql.Driver"
                dialect = org.hibernate.dialect.PostgreSQLDialect

                uri = new URI(property('LOCAL_DB_URL') ?: property('DATABASE_URL'))
                url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?ApplicationName=MK"

                username = uri.userInfo.split(":")[0]
                password = uri.userInfo.split(":")[1]

                logSql = true
            } else {
                dbCreate = "update"
                url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
            }
        }
    }
    production {

        grails.plugin.springsecurity.secureChannel.definition = [
                [pattern: '/**', access: 'REQUIRES_SECURE_CHANNEL']
        ]

        grails.plugin.springsecurity.rememberMe.useSecureCookie = true

        dataSource {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect

            uri = new URI(property('LOCAL_DB_URL') ?: property('DATABASE_URL'))
            url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?ApplicationName=MK"

            username = uri.userInfo.split(":")[0]
            password = uri.userInfo.split(":")[1]

            logSql = false
        }
    }
}
