// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {

    //開發用
    development {
        grails.logging.jul.usebridge = true
        grails.serverURL = "http://localhost:7070"
        fontrip.envMode = "localMode"//本機環境
        fontrip.mail.host = "smtp.sendgrid.net"
        fontrip.fonts.path = "C:\\WINDOWS\\Fonts\\KAIU.TTF"

        //Call 玉山銀行CardLink api 參數
        esunBank.cardLink.url.domainName = "https://cardtest.esunbank.com.tw"
        //Call 玉山銀行acq api 參數
        esunBank.acq.url.domainName = "https://acqtest.esunbank.com.tw"

        //Call Lion 紙本票券 api 參數
        lion.apiToken.url.domainName     = "https://ulionexapi.api.liontravel.com"
        lion.apiData.url.domainName      = "https://uetkt.api.liontravel.com"

        //Call Lion 團商品 api 參數
        lion.groupOrder.url.domainName   = "https://utours.api.liontravel.com"

        //Call 17Life api 參數
        seventeenLife.apiData.url.domainName = "http://iceman.17life.com"

        //Cloud Log Server
        log.cloud.domainName = "http://nas.fontrip.com:3000"

        //Call THSR
        thsr.b2b.url ="https://ticket_dev.thsrc.com.tw:6443"
        thsr.b2b.agentCode = "ITRIAPI"
        thsr.b2b.agentPWD = "abcd1234"

        //Cloud File
        fontrip.cloudFile.upload ="http://nas.fontrip.com/cloudFile/file/upload"
        fontrip.cloudFile.download ="http://nas.fontrip.com/cloudFile/file/get"
        fontrip.cloudFile.key ="bfb38909511d610f7bb65d0086b387a4"
        fontrip.cloudFile.secret ="9691000a80d62ab7738f2e32aec229e9"
    }
    //測試環境
    fontrip_dev {
        grails.logging.jul.usebridge = false
        grails.serverURL = "http://travelto.xervice.in"
        fontrip.envMode = "developerMode" //正式環境productionMode;測試環境developerMode
        fontrip.mail.host = "smtp.sendgrid.net"
        fontrip.fonts.path = "/usr/share/fonts/truetype/msfonts/KAIU.TTF"

        //Call 玉山銀行CardLink api 參數
        esunBank.cardLink.url.domainName = "https://cardtest.esunbank.com.tw"
        //Call 玉山銀行acq api 參數
        esunBank.acq.url.domainName = "https://acqtest.esunbank.com.tw"

        //Call Lion 紙本票券 api 參數
        lion.apiToken.url.domainName     = "https://ulionexapi.api.liontravel.com"
        lion.apiData.url.domainName      = "https://uetkt.api.liontravel.com"

        //Call Lion 團商品 api 參數
        lion.groupOrder.url.domainName   = "https://utours.api.liontravel.com"

        //Call 17Life api 參數
        seventeenLife.apiData.url.domainName = "http://iceman.17life.com/api/sharedeal"

        //Cloud Log Server
        log.cloud.domainName = "http://nas.fontrip.com:3000"

        //Call THSR
        thsr.b2b.url ="https://ticket_dev.thsrc.com.tw:6443"
        thsr.b2b.agentCode = "ITRIAPI"
        thsr.b2b.agentPWD = "abcd1234"

        //Cloud File
        fontrip.cloudFile.upload ="http://nas.fontrip.com/cloudFile/file/upload"
        fontrip.cloudFile.download ="http://nas.fontrip.com/cloudFile/file/get"
        fontrip.cloudFile.key ="bfb38909511d610f7bb65d0086b387a4"
        fontrip.cloudFile.secret ="9691000a80d62ab7738f2e32aec229e9"

    }
    //測試環境_旅途中(即將刪除，用development_lion取代之)
    fontrip_demo {
        grails.logging.jul.usebridge = false
        grails.serverURL = "http://travelto.xervice.in/fontrip"
        fontrip.envMode = "developerMode" //正式環境productionMode;測試環境developerMode
        fontrip.mail.host = "smtp.sendgrid.net"
        fontrip.fonts.path = "/usr/share/fonts/truetype/msfonts/KAIU.TTF"

        //Call 玉山銀行CardLink api 參數
        esunBank.cardLink.url.domainName = "https://cardtest.esunbank.com.tw"
        //Call 玉山銀行acq api 參數
        esunBank.acq.url.domainName = "https://acqtest.esunbank.com.tw"

        //Call Lion 紙本票券 api 參數
        lion.apiToken.url.domainName     = "https://ulionexapi.api.liontravel.com"
        lion.apiData.url.domainName      = "https://uetkt.api.liontravel.com"

        //Call Lion 團商品 api 參數
        lion.groupOrder.url.domainName   = "https://utours.api.liontravel.com"

        //Call 17Life api 參數
        seventeenLife.apiData.url.domainName = "http://iceman.17life.com"

        //Cloud Log Server
        log.cloud.domainName = "http://nas.fontrip.com:3000"

        //Call THSR
        thsr.b2b.url ="https://ticket_dev.thsrc.com.tw:6443"
        thsr.b2b.agentCode = "ITRIAPI"
        thsr.b2b.agentPWD = "abcd1234"

        //Cloud File
        fontrip.cloudFile.upload ="http://nas.fontrip.com/cloudFile/file/upload"
        fontrip.cloudFile.download ="http://nas.fontrip.com/cloudFile/file/get"
        fontrip.cloudFile.key ="bfb38909511d610f7bb65d0086b387a4"
        fontrip.cloudFile.secret ="9691000a80d62ab7738f2e32aec229e9"

    }
    //測試環境_旅途中
    development_lion {
        grails.logging.jul.usebridge = false
        grails.serverURL = "http://travelto.xervice.in/fontrip"
        fontrip.envMode = "developerMode" //正式環境productionMode;測試環境developerMode
        fontrip.mail.host = "smtp.sendgrid.net"
        fontrip.fonts.path = "/usr/share/fonts/truetype/msfonts/KAIU.TTF"

        //Call 玉山銀行CardLink api 參數
        esunBank.cardLink.url.domainName = "https://cardtest.esunbank.com.tw"
        //Call 玉山銀行acq api 參數
        esunBank.acq.url.domainName = "https://acqtest.esunbank.com.tw"

        //Call Lion 紙本票券 api 參數
        lion.apiToken.url.domainName     = "https://ulionexapi.api.liontravel.com"
        lion.apiData.url.domainName      = "https://uetkt.api.liontravel.com"

        //Call Lion 團商品 api 參數
        lion.groupOrder.url.domainName   = "https://utours.api.liontravel.com"

        //Call 17Life api 參數
        seventeenLife.apiData.url.domainName = "http://iceman.17life.com"

        //Cloud Log Server
        log.cloud.domainName = "http://nas.fontrip.com:3000"

        //Call THSR
        thsr.b2b.url ="https://ticket_dev.thsrc.com.tw:6443"
        thsr.b2b.agentCode = "ITRIAPI"
        thsr.b2b.agentPWD = "abcd1234"

        //Cloud File
        fontrip.cloudFile.upload ="http://nas.fontrip.com/cloudFile/file/upload"
        fontrip.cloudFile.download ="http://nas.fontrip.com/cloudFile/file/get"
        fontrip.cloudFile.key ="bfb38909511d610f7bb65d0086b387a4"
        fontrip.cloudFile.secret ="9691000a80d62ab7738f2e32aec229e9"

    }

    //正式環境_旅途中
    production_lion {
        grails.logging.jul.usebridge = false
        grails.serverURL = "https://www.journeyonapp.com"
        fontrip.envMode = "productionMode" //正式環境productionMode;測試環境developerMode
        fontrip.mail.host = "smtp.sendgrid.net"
        fontrip.fonts.path = "/usr/share/fonts/truetype/msfonts/KAIU.TTF"

        //Call 玉山銀行CardLink api 參數
        esunBank.cardLink.url.domainName = "https://card.esunbank.com.tw"
        //Call 玉山銀行acq api 參數
        esunBank.acq.url.domainName = "https://acq.esunbank.com.tw"

        //Call Lion 紙本票券 api 參數
        lion.apiToken.url.domainName     = "https://lionexapi.api.liontravel.com"
        lion.apiData.url.domainName      = "https://etkt.api.liontravel.com"

        //Call Lion 團商品 api 參數
        lion.groupOrder.url.domainName   = "https://tours.api.liontravel.com"

        //Call 17Life api 參數
        seventeenLife.apiData.url.domainName = "http://www.17life.com"

        //Cloud Log Server
        log.cloud.domainName = "http://nas.fontrip.com:3000"

        //Call THSR(待修改，尚在測試階段)
        thsr.b2b.url ="https://ticket_dev.thsrc.com.tw:6443"
        thsr.b2b.agentCode = "ITRIAPI"
        thsr.b2b.agentPWD = "abcd1234"

        //Cloud File
        fontrip.cloudFile.upload ="http://nas.fontrip.com/cloudFile/file/upload"
        fontrip.cloudFile.download ="http://nas.fontrip.com/cloudFile/file/get"
        fontrip.cloudFile.key ="bfb38909511d610f7bb65d0086b387a4"
        fontrip.cloudFile.secret ="9691000a80d62ab7738f2e32aec229e9"
    }

    //正式環境
    fontrip {
        grails.logging.jul.usebridge = false
        grails.serverURL = "https://platform.welcometw.com"
        fontrip.envMode = "productionMode" //正式環境productionMode;測試環境developerMode
        fontrip.mail.host = "smtp.sendgrid.net"
        fontrip.fonts.path = "/usr/share/fonts/truetype/msfonts/KAIU.TTF"

        //Call 玉山銀行CardLink api 參數
        esunBank.cardLink.url.domainName = "https://card.esunbank.com.tw"
        //Call 玉山銀行acq api 參數
        esunBank.acq.url.domainName = "https://acq.esunbank.com.tw"

        //Call Lion 紙本票券 api 參數
        lion.apiToken.url.domainName     = "https://lionexapi.api.liontravel.com"
        lion.apiData.url.domainName      = "https://etkt.api.liontravel.com"

        //Call Lion 團商品 api 參數
        lion.groupOrder.url.domainName   = "https://tours.api.liontravel.com"

        //Call 17Life api 參數
        seventeenLife.apiData.url.domainName = "https://www.17life.com"

        //Cloud Log Server
        log.cloud.domainName = "http://nas.fontrip.com:3000"

        //Call THSR(待修改，尚在測試階段)
        thsr.b2b.url ="https://ticket_dev.thsrc.com.tw:6443"
        thsr.b2b.agentCode = "ITRIAPI"
        thsr.b2b.agentPWD = "abcd1234"

        //Cloud File
        fontrip.cloudFile.upload ="http://nas.fontrip.com/cloudFile/file/upload"
        fontrip.cloudFile.download ="http://nas.fontrip.com/cloudFile/file/get"
        fontrip.cloudFile.key ="bfb38909511d610f7bb65d0086b387a4"
        fontrip.cloudFile.secret ="9691000a80d62ab7738f2e32aec229e9"
    }

}

// log4j configuration
log4j.main = {
    appenders {
        console name: 'stdout', layout: pattern(conversionPattern: '[%-5p] %d{yyyy-MM-dd HH:mm:ss} %c{2} - %m%n')
    }

    appenders {
        appender new org.apache.log4j.RollingFileAppender(
                name: "file",
                maxFileSize: '25MB',
                file: "app.log",
                layout: pattern(conversionPattern: '[%-5p] %d{MM-dd HH:mm:ss} %c{2} - %m%n')
        )
    }

    root {
        info 'file','stdout'
    }
    error 'grails.app.services.org.grails.plugin.resource'
    error 'grails.app.taglib.org.grails.plugin.resource'
    error 'grails.app.resourceMappers.org.grails.plugin.resource'
    info 'org.codehaus.groovy.grails.web.servlet'// controllers
    info   'org.codehaus.groovy.grails.web.pages'          // GSP
    warn 'org.codehaus.groovy.grails.web.sitemesh'       // layouts
    error 'org.codehaus.groovy.grails.web.mapping.filter' // URL mapping
    error 'org.codehaus.groovy.grails.web.mapping'        // URL mapping
    error 'org.codehaus.groovy.grails.commons'            // core / classloading
    error 'org.codehaus.groovy.grails.plugins'            // plugins
    error'org.codehaus.groovy.grails.orm.hibernate'      // hibernate integration
    error 'org.springframework'
    error 'org.hibernate'
    error'net.sf.ehcache.hibernate'
}

grails {
    mail {
//		host = "mail.xervice.in"
//		port = 25
//		username = "noreply@xervice.in"
//		password = "XzL7ME4Z,3(1"
//		props = ["mail.smtp.auth":"true", "mail.smtp.starttls.enable": "false"]
//		host = "cp26.g-dns.com"
//		port = 465
//		username = "noreply@fontrip.com.tw"
//		password = "A29FJtcFXpvs"
//		props = ["mail.smtp.auth":"true", "mail.smtp.starttls.enable": "false"]

        username="fontrip"
        password="itri02750963"
        host="smtp.sendgrid.net"
        port=587
        props = ["mail.smtp.auth":"true", "mail.smtp.starttls.enable": "false"]



    }

}





// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'account.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'account.UserRole'
grails.plugin.springsecurity.authority.className = 'account.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':                ['permitAll'],
	'/index':           ['permitAll'],
	'/index.gsp':       ['permitAll'],
	'/assets/**':       ['permitAll'],
	'/**/js/**':        ['permitAll'],
	'/**/css/**':       ['permitAll'],
	'/**/images/**':    ['permitAll'],
	'/**/favicon.ico':  ['permitAll']
]



// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fontrip.account.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fontrip.account.UserRole'
grails.plugin.springsecurity.authority.className = 'fontrip.account.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':                ['permitAll'],
	'/index':           ['permitAll'],
	'/index.gsp':       ['permitAll'],
	'/assets/**':       ['permitAll'],
	'/**/js/**':        ['permitAll'],
	'/**/css/**':       ['permitAll'],
	'/**/images/**':    ['permitAll'],
	'/**/favicon.ico':  ['permitAll']
]



// Added by the Spring Security Core plugin:
//grails.plugin.springsecurity.successHandler.defaultTargetUrl = "/login/authSuccess"
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fontrip.account.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fontrip.account.UserRole'
grails.plugin.springsecurity.authority.className = 'fontrip.account.Role'
grails.plugin.springsecurity.authority.groupAuthorityNameField = 'authorities'
grails.plugin.springsecurity.useRoleGroups = true
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':                ['permitAll'],
	'/index':           ['permitAll'],
	'/index.gsp':       ['permitAll'],
	'/assets/**':       ['permitAll'],
	'/**/js/**':        ['permitAll'],
	'/**/css/**':       ['permitAll'],
	'/**/images/**':    ['permitAll'],
	'/**/favicon.ico':  ['permitAll']
]


