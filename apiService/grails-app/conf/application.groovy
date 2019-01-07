def property(String name) {
    return System.env[name] ?: System.getProperty(name)
}

grails.plugin.console.enabled = true

grails.databinding.dateFormats = [
        'MM/dd/yyyy hh:mm a', 'MMddyyyy', 'MM/dd/yyyy'
]

grails.serverURL = property('SERVER_URL') ?: "https://api.mk.com"

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/',                        access: ['permitAll']],
        [pattern: '/error',                   access: ['permitAll']],
        [pattern: '/index',                   access: ['permitAll']],
        [pattern: '/login/**',                access: ['permitAll', 'IS_AUTHENTICATED_REMEMBERED']],
        [pattern: '/api/login/**',            access: ['permitAll', 'IS_AUTHENTICATED_REMEMBERED']],
        [pattern: '/api/logout/**',           access: ['permitAll']],
        [pattern: '/logout/**',               access: ['permitAll']],
        [pattern: '/api/register/**',         access: ['permitAll']],
        [pattern: '/api/version',             access: ['permitAll']],
        [pattern: '/api/reset',               access: ['permitAll']],
        [pattern: '/api/forgotPassword',      access: ['permitAll']],
        [pattern: '/api/verifyRegistration',  access: ['permitAll']],
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/api/version',             filters: 'none'],
        [pattern: '/api/register/**',         filters: 'none'],
        [pattern: '/api/verifyRegistration',  filters: 'none'],
        [pattern: '/api/reset',               filters: 'none'],
        [pattern: '/api/logout',              filters: 'none'],
        [pattern: '/api/forgotPassword',      filters: 'none'],
        [pattern: '/api/**',                  filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'],
]
