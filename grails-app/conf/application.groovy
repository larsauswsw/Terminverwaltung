

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.rejectIfNoRule = true
grails.plugin.springsecurity.userLookup.userDomainClassName = 'terminverwaltung.SpringUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'terminverwaltung.SpringUserSpringRole'
grails.plugin.springsecurity.authority.className = 'terminverwaltung.SpringRole'
grails.plugin.springsecurity.requestMap.className = 'terminverwaltung.Requestmap'
grails.plugin.springsecurity.securityConfigType = 'Requestmap'
grails.plugin.springsecurity.roleHierarchyEntryClassName = 'terminverwaltung.RoleHierarchy'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]




