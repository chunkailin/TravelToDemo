package fontrip

import fontrip.account.AdminRoleGroup
import fontrip.account.AgentRoleGroup
import fontrip.account.Role
import fontrip.account.RoleGroup
import fontrip.account.RoleGroupRole
import fontrip.account.User
import fontrip.location.Area
import fontrip.location.Country
import fontrip.location.Location
import fontrip.location.LocationAreaMapping
import fontrip.poi.Channel
import fontrip.poi.ChannelDetail
import grails.util.Environment
import groovy.json.JsonSlurper
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.codehaus.groovy.grails.commons.DefaultGrailsControllerClass
import org.grails.plugins.localization.Localization

import grails.transaction.Transactional

@Transactional
class SystemService {
    def grailsApplication
    def ToolService
    def fileService

    def loadCountry(){
        println("======Load Country Start======")
        long startTime = (new Date()).getTime();

        InputStreamReader isr
        if(new File("sql/country.json").exists()){
            isr= new InputStreamReader(new FileInputStream("sql/country.json"), "UTF-8");
        }else{
            isr= new InputStreamReader(new FileInputStream("/home/project/FontripServer/sql/country.json"), "UTF-8");
        }

        BufferedReader reader = new BufferedReader(isr);

        def jsonSlurper = new JsonSlurper();
        def parsedData = jsonSlurper.parse(reader)

        int saveCount=0
        HashMap countryError= new HashMap()

        parsedData.each {row ->

            if(row[0]!=null ){
                println row[0].trim()
                Country country = Country.findByIsoCodeAlpha2(row[0].trim())
                if(!country ) {
                    def coord = row[9].trim().split(",")
                    country = new Country(
                            isoCodeAlpha2:row[0].trim(),
                            isoCodeAlpha3:row[1].trim(),
                            isoCodeNumeric:row[2],
                            searchField:(row[0].trim() + "," + row[1].trim()+","+row[2]),
                            continentalCode:row[3],
                            worldSubRegionCode:row[4]
                    )
                    if(coord && coord.size()==2){
                        country.latitude = Double.parseDouble(coord[0]);
                        country.longitude = Double.parseDouble(coord[1]);
                    }
                    country.save(flush:true);
                    if(row[67].trim()!="" ){
                        if(row[67].trim()=="en-us" && !Localization.findByCodeAndLocale("country.code."+country.isoCodeAlpha3,"*")){
                            new Localization(
                                    code:"country.name."+country.isoCodeAlpha3,
                                    locale:"*",
                                    text:row[70].trim()
                            ).save(flush:true);
                        }else if(row[67].trim()=="zh-un" && !Localization.findByCodeAndLocale("country.code."+country.isoCodeAlpha3,"zhCN")){
                            new Localization(
                                    code:"country.name."+country.isoCodeAlpha3,
                                    locale:"zhCN",
                                    text:row[70].trim()
                            ).save(flush:true);

                        }
                    }
                }else{

                    if(row[67].trim()!="" ){

                        if(row[67].trim()=="en-us" && !Localization.findByCodeAndLocale("country.code."+country.isoCodeAlpha3,"*")){
                            new Localization(
                                    code:"country.name."+country.isoCodeAlpha3,
                                    locale:"*",
                                    text:row[70].trim()
                            ).save(flush:true);
                        }else if(row[67].trim()=="zh-un" || row[67].trim()=="zh-fao") {
                            if( !Localization.findByCodeAndLocale("country.code."+country.isoCodeAlpha3,"zhCN")){
                                def loc = new Localization(
                                        code:"country.name."+country.isoCodeAlpha3,
                                        locale:"zhCN",
                                        text:row[70].trim()
                                ).save(flush:true);
                            }
                            if( !Localization.findByCodeAndLocale("country.code."+country.isoCodeAlpha3,"zhTW")){
                                def loc = new Localization(
                                        code:"country.name."+country.isoCodeAlpha3,
                                        locale:"zhTW",
                                        text:row[70].trim()
                                ).save(flush:true);
                            }

                        }
                    }
                }



            }

        }

        println("Uses "+Math.round((new Date()).getTime()-startTime)/(1000)+" secs")
        println("======Load Country Finish======")
    }

    def loadTaiwanCity(){



        println("======Load Taiwan City Start======")
        long startTime = (new Date()).getTime();

        InputStreamReader isr
        if(new File("sql/TWN_city_area.json").exists()){
            isr= new InputStreamReader(new FileInputStream("sql/TWN_city_area.json"), "UTF-8");
        }else{
            isr= new InputStreamReader(new FileInputStream("/home/project/FontripServer/sql/TWN_city_area.json"), "UTF-8");
        }

        BufferedReader reader = new BufferedReader(isr);

        def jsonSlurper = new JsonSlurper();
        def parsedData = jsonSlurper.parse(reader)
        Country country = Country.findByIsoCodeAlpha3("TWN")
        println "country="+country.isoCodeAlpha3
        parsedData.each {row ->
            Location location;
            if(row[0]!=null ){
                location = Location.findByLocationCode(row[0])
                if(!location ) {
                    def latitude=0.0, longitude=0.0
                    List<Double> gps = getGPS(row[1].trim())
                    if(gps!=null){
                        latitude=gps.get(0)
                        longitude=gps.get(1)
                    }
                    location = new Location(
                            country:country,
                            locationCode:row[0],
                            isoCodeNumeric:row[2],
                            searchField:(row[1].trim() + "," + row[2].trim()+","+row[0]),
                            continentalCode:country.continentalCode,
                            worldSubRegionCode:country.worldSubRegionCode,
                            latitude: latitude,
                            longitude: longitude,
                            enable:true
                    )
                }

                println "save location:"+row[1].trim() + ","+location.save(flush:true);
                if( !Localization.findByCodeAndLocale(location.nameCode,"*")){
                    new Localization(
                            code:location.nameCode,
                            locale:"*",
                            text:row[2].trim()
                    ).save(flush:true);
                }
                if( !Localization.findByCodeAndLocale(location.nameCode,"enUS")){
                    new Localization(
                            code:location.nameCode,
                            locale:"enUS",
                            text:row[2].trim()
                    ).save(flush:true);
                }
                if( !Localization.findByCodeAndLocale(location.nameCode,"zhCN")){
                    def loc = new Localization(
                            code:location.nameCode,
                            locale:"zhCN",
                            text:row[1].trim()
                    ).save(flush:true);
                }
                if( !Localization.findByCodeAndLocale(location.nameCode,"zhTW")){
                    def loc = new Localization(
                            code:location.nameCode,
                            locale:"zhTW",
                            text:row[1].trim()
                    ).save(flush:true);
                }


            }

            if(row[3]!=null ){
                Area area = Area.findByAreaCode(row[3])
                if(!area ) {
                    def latitude=0.0, longitude=0.0
                    List<Double> gps = getGPS(row[1].trim()+row[4].trim())
                    if(gps.get(0)!=null && gps.get(1)!=null){
                        latitude=gps.get(0)
                        longitude=gps.get(1)
                    }
                    area = new Area(
                            country:country,
                            areaCode:row[3],
                            searchField:(row[4].trim() + "," + row[5].trim()+","+row[3]),
                            latitude: latitude,
                            longitude: longitude,
                            enable: true
                    )
                }
                area.areaCode = row[3];
                //area.save(flush:true);
                println "save area:"+row[4].trim() + ","+area.save(flush:true);
                sleep(500)
                if(!LocationAreaMapping.findByLocationAndArea(location,area)){
                    def mapping = new LocationAreaMapping(location:location,area:area)
                    if (!mapping.save(flush:true)) {
                        mapping.errors.each {
                            println it
                        }
                    }
                }

                if( !Localization.findByCodeAndLocale(area.nameCode,"*")){
                    new Localization(
                            code:area.nameCode,
                            locale:"*",
                            text:row[5].trim()
                    ).save(flush:true);
                }
                if( !Localization.findByCodeAndLocale(area.nameCode,"enUS")){
                    new Localization(
                            code:area.nameCode,
                            locale:"enUS",
                            text:row[5].trim()
                    ).save(flush:true);
                }
                if( !Localization.findByCodeAndLocale(area.nameCode,"zhCN")){
                    def loc = new Localization(
                            code:area.nameCode,
                            locale:"zhCN",
                            text:row[4].trim()
                    ).save(flush:true);
                }
                if( !Localization.findByCodeAndLocale(area.nameCode,"zhTW")){
                    def loc = new Localization(
                            code:area.nameCode,
                            locale:"zhTW",
                            text:row[4].trim()
                    ).save(flush:true);
                }


            }

        }




        println("Uses "+Math.round((new Date()).getTime()-startTime)/(1000)+" secs")
        println("======Load Taiwan City Finish======")

        Localization.resetAll()

        println ("======Load Taiwan City Successfully======")
    }

    def createDefaultRoleData(){
        //create admin role list
        println("======Load Role List======")
        long startTime = (new Date()).getTime();

        InputStreamReader isr
        if(new File("grails-app/conf/systemRoleList.json").exists()){
            isr= new InputStreamReader(new FileInputStream("grails-app/conf/systemRoleList.json"), "UTF-8");
        }else{
            isr= new InputStreamReader(new FileInputStream("/home/project/FontripServer/grails-app/conf/systemRoleList.json"), "UTF-8");
        }

        BufferedReader reader = new BufferedReader(isr);

        def jsonSlurper = new JsonSlurper();
        def parsedData = jsonSlurper.parse(reader)


        println("======Load Admin Role List======")
        def adminRoleList = parsedData.adminRoleList

        adminRoleList.each {row ->
            String roleName = row.authority
            String code = "roleGroup.name."+roleName
            if(!Role.findByAuthority(roleName)){
                new Role(roleName).save(flush:  true)
            }
        }

        println("======Create Default Admin RoleGroup======")
        def adminRoleGroupApiManager = AdminRoleGroup.findByName("GROUP_ADMIN_API_MANAGER")

        //GROUP_ADMIN_API_MANAGER
        if(!adminRoleGroupApiManager){
            adminRoleGroupApiManager =  new AdminRoleGroup(name:"GROUP_ADMIN_API_MANAGER",defaultRoleGroup:true).save(flush: true)
        }
        RoleGroupRole.create(adminRoleGroupApiManager,Role.findByAuthority("ROLE_ADMIN_API_CREATE"),true)
        RoleGroupRole.create(adminRoleGroupApiManager,Role.findByAuthority("ROLE_ADMIN_API_EDIT"),true)
        RoleGroupRole.create(adminRoleGroupApiManager,Role.findByAuthority("ROLE_ADMIN_API_DELETE"),true)
        RoleGroupRole.create(adminRoleGroupApiManager,Role.findByAuthority("ROLE_ADMIN_API_VIEW"),true)

        //GROUP_ADMIN_API_MAINTENANCE
        def adminRoleGroupApiMaintenance = AdminRoleGroup.findByName("GROUP_ADMIN_API_MAINTENANCE")
        if(!adminRoleGroupApiMaintenance){
            adminRoleGroupApiMaintenance =  new AdminRoleGroup(name:"GROUP_ADMIN_API_MAINTENANCE",defaultRoleGroup:true).save(flush: true)
        }
        RoleGroupRole.create(adminRoleGroupApiMaintenance,Role.findByAuthority("ROLE_ADMIN_API_EDIT"),true)
        RoleGroupRole.create(adminRoleGroupApiMaintenance,Role.findByAuthority("ROLE_ADMIN_API_VIEW"),true)

        //GROUP_ADMIN_SYS_CONFIG
        def adminRoleGroupSysConfig = AdminRoleGroup.findByName("GROUP_ADMIN_SYS_CONFIG")
        if(!adminRoleGroupSysConfig){
            adminRoleGroupSysConfig =  new AdminRoleGroup(name:"GROUP_ADMIN_SYS_CONFIG",defaultRoleGroup:true).save(flush: true) ;
        }
        RoleGroupRole.create(adminRoleGroupSysConfig,Role.findByAuthority("ROLE_ADMIN_SYS_CONFIG_EDIT"),true)
        RoleGroupRole.create(adminRoleGroupSysConfig,Role.findByAuthority("ROLE_ADMIN_SYS_CONFIG_VIEW"),true)
        RoleGroupRole.create(adminRoleGroupSysConfig,Role.findByAuthority("ROLE_ADMIN_TRAVELNOTE_CATEGORY"),true)
        RoleGroupRole.create(adminRoleGroupSysConfig,Role.findByAuthority("ROLE_ADMIN_POI_CATEGORY"),true)
        RoleGroupRole.create(adminRoleGroupSysConfig,Role.findByAuthority("ROLE_ADMIN_TAG_MANAGEMENT"),true)
        RoleGroupRole.create(adminRoleGroupSysConfig,Role.findByAuthority("ROLE_ADMIN_COUNTRY_MANAGEMENT"),true)

        //GROUP_ADMIN_BANNER_MANAGER
        def adminRoleBannerManager = AdminRoleGroup.findByName("GROUP_ADMIN_BANNER_MANAGER")
        if(!adminRoleBannerManager){
            adminRoleBannerManager =  new AdminRoleGroup(name:"GROUP_ADMIN_BANNER_MANAGER",defaultRoleGroup:true).save(flush: true) ;
        }
        RoleGroupRole.create(adminRoleBannerManager,Role.findByAuthority("ROLE_ADMIN_BANNER_CREATE"),true)
        RoleGroupRole.create(adminRoleBannerManager,Role.findByAuthority("ROLE_ADMIN_BANNER_EDIT"),true)
        RoleGroupRole.create(adminRoleBannerManager,Role.findByAuthority("ROLE_ADMIN_BANNER_DELETE"),true)
        RoleGroupRole.create(adminRoleBannerManager,Role.findByAuthority("ROLE_ADMIN_BANNER_VIEW"),true)

        //GROUP_ADMIN_ACCOUNT_MANAGER
        def adminRoleAccountManager = AdminRoleGroup.findByName("GROUP_ADMIN_ACCOUNT_MANAGER")
        if(!adminRoleAccountManager){
            adminRoleAccountManager =  new AdminRoleGroup(name:"GROUP_ADMIN_ACCOUNT_MANAGER",defaultRoleGroup:true).save(flush: true) ;
        }
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_STORE_CREATE"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_STORE_EDIT"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_STORE_DELETE"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_STORE_VIEW"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_STORE_CONFIG"),true)

        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_AGENT_CREATE"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_AGENT_EDIT"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_AGENT_DELETE"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_AGENT_VIEW"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_AGENT_CONFIG"),true)

        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_SPOT_CREATE"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_SPOT_EDIT"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_SPOT_DISABLE"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_SPOT_VIEW"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_SPOT_CONFIG"),true)

        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_CHANNEL_CREATE"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_CHANNEL_EDIT"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_CHANNEL_DISABLE"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_CHANNEL_VIEW"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_CHANNEL_CONFIG"),true)

        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_EXPERT_CREATE"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_EXPERT_EDIT"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_EXPERT_DISABLE"),true)
        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_EXPERT_VIEW"),true)

        RoleGroupRole.create(adminRoleAccountManager,Role.findByAuthority("ROLE_ADMIN_AUTHORITY_MANAGEMENT"),true)

        //GROUP_ADMIN_FINANCIAL_MANAGER
        def adminRoleFinancialManager = AdminRoleGroup.findByName("GROUP_ADMIN_FINANCIAL_MANAGER")
        if(!adminRoleFinancialManager){
            adminRoleFinancialManager =  new AdminRoleGroup(name:"GROUP_ADMIN_FINANCIAL_MANAGER",defaultRoleGroup:true).save(flush: true) ;
        }
        RoleGroupRole.create(adminRoleFinancialManager,Role.findByAuthority("ROLE_ADMIN_FINANCIAL_MANAGEMENT"),true)

        //GROUP_ADMIN_ARTICLE_MANAGER
        def adminRoleArticleManager = AdminRoleGroup.findByName("GROUP_ADMIN_ARTICLE_MANAGER")
        if(!adminRoleArticleManager){
            adminRoleArticleManager =  new AdminRoleGroup(name:"GROUP_ADMIN_ARTICLE_MANAGER",defaultRoleGroup:true).save(flush: true) ;
        }
        RoleGroupRole.create(adminRoleArticleManager,Role.findByAuthority("ROLE_ADMIN_TRAVELNOTE"),true)
        RoleGroupRole.create(adminRoleArticleManager,Role.findByAuthority("ROLE_ADMIN_EXPERT_CONFIG"),true)
        RoleGroupRole.create(adminRoleArticleManager,Role.findByAuthority("ROLE_ADMIN_ANNOUNCEMENT_MANAGEMENT"),true)


        println("======Load Store Role List======")
        parsedData.storeRoleList.each {row ->
            String roleName = row.authority
            String code = "roleGroup.name."+roleName
            if(!Role.findByAuthority(roleName)){
                new Role(roleName).save(flush:  true)
            }


        }

        println("======Load Branch Role List======")
        parsedData.branchRoleList.each {row ->
            String roleName = row.authority
            String code = "roleGroup.name."+roleName
            if(!Role.findByAuthority(roleName)){
                new Role(roleName).save(flush:  true)
            }


        }


        println("======Load Agent Role List======")
        parsedData.agentRoleList.each {row ->
            String roleName = row.authority
            String code = "roleGroup.name."+roleName
            if(!Role.findByAuthority(roleName)){
                new Role(roleName).save(flush:  true)
            }
        }
        //處理Agent的權限模組
        //1.APP操作人員
        def agentAppManager = AgentRoleGroup.findByName("GROUP_AGENT_APP_USER")
        if(!agentAppManager){
            agentAppManager =  new AgentRoleGroup(name:"GROUP_AGENT_APP_USER",defaultRoleGroup:true).save(flush: true) ;
        }
        RoleGroupRole.create(agentAppManager,Role.findByAuthority("ROLE_AGENT_APP_SERVICE"),true)

        println("======Load Channel Role List======")
        parsedData.channelRoleList.each {row ->
            String roleName = row.authority
            String code = "roleGroup.name."+roleName
            if(!Role.findByAuthority(roleName)){
                new Role(roleName).save(flush:  true)
            }
        }

        println("======Load Role List Finish======")

        println("======Load General Role List======")
        parsedData.generalRoleList.each {row ->
            String roleName = row.authority
            String code = "roleGroup.name."+roleName
            if(!Role.findByAuthority(roleName)) {
                new Role(roleName).save(flush: true)
            }

        }
        println("======Load Basic Role Group======")
        //GROUP_USER
        def basicRoleGroup = RoleGroup.findByNameAndDefaultRoleGroup("GROUP_USER",true)
        if(!basicRoleGroup){
            basicRoleGroup =  new RoleGroup(name:"GROUP_USER",defaultRoleGroup:true).save(flush: true) ;
        }
        RoleGroupRole.create(basicRoleGroup,Role.findByAuthority("ROLE_USER"),true)

        println("Uses "+Math.round((new Date()).getTime()-startTime)/(1000)+" secs")
        println("======Load General List Finish======")

    }

    def checkAccessFunction(){
        def nowAccessFunctionIdList = []
        grailsApplication.controllerClasses.each { DefaultGrailsControllerClass controller ->
            Class controllerClass = controller.clazz

        }
    }

    def createSysConfig(){
        //config.system.fileupload
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_UPLOADLIMIT)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_UPLOADLIMIT,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin: true,
                    required:true,
                    regularExpression:'^[1-20]$',
                    configValue:'10'
            ).save(flush:true);
        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_MIN_WIDTH)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_MIN_WIDTH,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin: true,
                    required:true,
                    regularExpression:'^[800-2048]$',
                    configValue:'1024'
            ).save(flush:true);
        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_MIN_HEIGHT)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_MIN_HEIGHT,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin: true,
                    required:true,
                    regularExpression:'^[800-2048]$',
                    configValue:'768'
            ).save(flush:true);
        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_EXTENSION)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_EXTENSION,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'[a-z0-9,]',
                    configValue:'jpg,jpeg,gif,png,bmp'
            ).save(flush:true);
        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_DEFAULTSIZE)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_DEFAULTSIZE,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin:false,
                    required:true,
                    regularExpression:'[0-9]',
                    configValue:'1024,340x300'
            ).save(flush:true);
        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_AVAILABLESIZE)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_AVAILABLESIZE,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin:false,
                    required:true,
                    regularExpression:'[x0-9,]',
                    configValue:'1024,560x460,128x128,600x400,340x300,150x100,1920x607,1420x1420,600x250,768x768,1024x684,400x400' +
                            ',640x482,562x384,100x100,250x166,526x348,375x282' //旅途中App使用之圖片尺寸
            ).save(flush:true);
        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_FILE_UPLOADLIMIT)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_FILE_UPLOADLIMIT,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'^[1-20]$',
                    configValue:'10'
            ).save(flush:true);
        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_FILE_EXTENSION)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_FILE_EXTENSION,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'[a-z0-9,]',
                    configValue:'*'
            ).save(flush:true);
        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_AUDIO_UPLOADLIMIT)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_AUDIO_UPLOADLIMIT,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'^[1-50]$',
                    configValue:'30'
            ).save(flush:true);
        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_AUDIO_EXTENSION)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_AUDIO_EXTENSION,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'[a-z0-9,]',
                    configValue:'mp3'
            ).save(flush:true);
        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_VIDEO_UPLOADLIMIT)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_VIDEO_UPLOADLIMIT,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'^[1-200]$',
                    configValue:'100'
            ).save(flush:true);
        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_VIDEO_EXTENSION)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_VIDEO_EXTENSION,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'[a-z0-9,]',
                    configValue:'mp4'
            ).save(flush:true);
        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_UPLOAD_PATH)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_UPLOAD_PATH,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin:false,
                    required:true,
                    regularExpression:'[a-z0-9,]',
                    configValue:'/home/FonTrip/'
            ).save(flush:true);
        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_GENERAL,SysConfig.CONFIG_CODE_SYSTEM_GENERAL_DEBUG)){
            //config.system.general
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_GENERAL,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_GENERAL_DEBUG,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'(true|false)',
                    configValue:'true'
            ).save(flush:true);

        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_GENERAL,SysConfig.CONFIG_CODE_SYSTEM_GENERAL_LOCALE)){
            //config.system.general
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_GENERAL,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_GENERAL_LOCALE,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM ,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'^([a-z]{2}_[A-Z]{2},){1,}([a-z]{2}_[A-Z]{2}$)',
                    configValue:'zh_TW,en_US,ja_JP,zh_CN'
            ).save(flush:true);

        }

        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_VALIDATE,SysConfig.CONFIG_CODE_SYSTEM_VALIDATE_EMAIL_TIME)){
            //config.system.general
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_VALIDATE,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_VALIDATE_EMAIL_TIME,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM ,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'^[1-60]$',
                    configValue:'60'
            ).save(flush:true);

        }
        //建立遠端FTP檔案備份設定
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FTP,SysConfig.CONFIG_CODE_SYSTEM_FTP_SERVER)){
            //config.system.general
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FTP,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FTP_SERVER,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM ,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'[a-z0-9,.]',
                    configValue:grailsApplication.config.cloudSync.ftp.server
            ).save(flush:true);

        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FTP,SysConfig.CONFIG_CODE_SYSTEM_FTP_USERNAME)){
            //config.system.general
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FTP,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FTP_USERNAME,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM ,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'[a-z0-9,.@]',
                    configValue:grailsApplication.config.cloudSync.ftp.username
            ).save(flush:true);

        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FTP,SysConfig.CONFIG_CODE_SYSTEM_FTP_PASSWORD)){
            //config.system.general
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FTP,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FTP_PASSWORD,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM ,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'[a-z0-9,.]',
                    configValue:grailsApplication.config.cloudSync.ftp.password
            ).save(flush:true);

        }
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FTP,SysConfig.CONFIG_CODE_SYSTEM_FTP_BASEFOLDER)){
            //config.system.general
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_FTP,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_FTP_BASEFOLDER,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM ,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'[a-z0-9,./]',
                    configValue:grailsApplication.config.cloudSync.ftp.folder
            ).save(flush:true);

        }

        //建立第三方API KEY設定
        //1.) Google Api Key
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_GOOGLE_API,SysConfig.CONFIG_CODE_SYSTEM_GOOGLE_API_KEY)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_GOOGLE_API,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_GOOGLE_API_KEY,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM ,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'[a-z0-9,.]',
                    configValue:"AIzaSyCjKoZYMoD4byPcuWrcNF2GprjacWIHGr0"
            ).save(flush:true);

        }
        //建立第三方API KEY設定
        //1.) Google Api Key(SERVER)
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_GOOGLE_API,SysConfig.CONFIG_CODE_SYSTEM_GOOGLE_SERVER_API_KEY)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_GOOGLE_API,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_GOOGLE_SERVER_API_KEY,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM ,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'[a-z0-9,.]',
                    configValue:"AIzaSyA2Di7t1k17imi67-4pJWN5z5NVnOGzD2g"
            ).save(flush:true);

        }

        //建立Could log Server設定
        // 1. ) appId
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_CLOUD_LOG,SysConfig.CONFIG_CODE_SYSTEM_CLOUD_LOG_APPID)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_CLOUD_LOG,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_CLOUD_LOG_APPID,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM ,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'[a-z0-9,.]',
                    configValue:"fontrip"
            ).save(flush:true);
        }

        // 1. ) secret
        if(!SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_CLOUD_LOG,SysConfig.CONFIG_CODE_SYSTEM_CLOUD_LOG_SECRET)){
            new SysConfig(
                    configGroup:SysConfig.CONFIG_GROUP_SYSTEM_CLOUD_LOG,
                    configCode:SysConfig.CONFIG_CODE_SYSTEM_CLOUD_LOG_SECRET,
                    scope:SysConfig.CONFIG_SCOPE_SYSTEM ,
                    editByAdmin:true,
                    required:true,
                    regularExpression:'[a-z0-9,.]',
                    configValue:"57f21b94eac4eb5fe64eee74"
            ).save(flush:true);
        }

    }

    def setFontripDefaultPhoto(){
        def fileName=FontripService.mainPhotoName
        def mainPhoto = SysFile.findByFileName(fileName)
        if(!mainPhoto){
            File file=new File("web-app/images/"+(Environment.current.name in ['fontrip_demo','development_lion','production_lion'])?"DefaultImagePhotoPreparing_Lion.jpg":"DefaultImagePhotoPreparing.jpg")
            if(!file.exists()){
                new File("/home/project/FonTripDevelopment/web-app/images/"+(Environment.current.name in ['fontrip_demo','development_lion','production_lion'])?"DefaultImagePhotoPreparing_Lion.jpg":"DefaultImagePhotoPreparing.jpg")
            }
            //檔案名稱抓系統時間 + random數字
            def targetFolder = fileService.uploadPath + fileService.UPLOAD_FOLDER_PHOTO + "/"
            String cloudFolder = "/backup/fontrip/cloudFile/"
            new File(targetFolder).mkdirs()
            String dtFilePath=targetFolder+fileName
            ToolService.copyFile(file, dtFilePath)
            mainPhoto = new SysFile()
            mainPhoto.folder= targetFolder
            mainPhoto.path=dtFilePath
            mainPhoto.fileName=fileName
            mainPhoto.name=fileName
            mainPhoto.description=fileName
            mainPhoto.size=file.size()
            mainPhoto.isPhoto=true
            mainPhoto.cloudFolder = cloudFolder
            mainPhoto.extension=StringService.getFileExtension(file.name)
            mainPhoto.dateUploaded = new Date()
            mainPhoto.save(flush: true)
        }
    }

    def List<Double> getGPS(String term){
        List<Double> gps = new ArrayList()
        try {
            String googleUrl = "http://maps.google.com/maps/api/geocode/json?sensor=false&address="
            HttpGet req = new HttpGet(googleUrl + term)
            HttpClient client = new DefaultHttpClient()
            HttpResponse response = client.execute(req)
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"))
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            def jsonSlurper = new JsonSlurper();
            def result = jsonSlurper.parseText(sb.toString())
            gps.add(result.results.geometry.location.lat[0])
            gps.add(result.results.geometry.location.lng[0])
        }catch (Exception ex){
            log.warn(term +":GET GPS ERROR")
            return null
        }
        return gps
    }

    def processLocations(){
        //處理首頁特定Location
        //新增墾丁跟日月潭

        Country country = Country.findByIsoCodeAlpha3("TWN")
        String CODE_SUN_MOON_LAKE = "SUN_MOON_LAKE"
        String CODE_KENTING = "KENTING"
        def location = Location.findByLocationCode(CODE_KENTING)
        if(!location){
            location = new Location(
                    country:country,
                    locationCode:CODE_KENTING,
                    continentalCode:country.continentalCode,
                    worldSubRegionCode:country.worldSubRegionCode,
                    latitude: 21.945367,
                    longitude: 120.7958463,searchField: "墾丁,KenTing" ,
                    enable:true
            ).save(flush: true)
            if( !Localization.findByCodeAndLocale(location.nameCode,"*")){
                new Localization(
                        code:location.nameCode,
                        locale:"*",
                        text:"Ken-Ting"
                ).save(flush:true);
            }

            if( !Localization.findByCodeAndLocale(location.nameCode,"zhTW")){
                def loc = new Localization(
                        code:location.nameCode,
                        locale:"zhTW",
                        text: "墾丁"
                ).save(flush:true);
            }
            if( !Localization.findByCodeAndLocale(location.briefCode,"*")){
                new Localization(
                        code:location.briefCode,
                        locale:"*",
                        text:"Kenting is the paradise of leisure and fun, as well as the home to tropical forests, meadows, and seaside cliffs."
                ).save(flush:true);
            }

            if( !Localization.findByCodeAndLocale(location.briefCode,"zhTW")){
                def loc = new Localization(
                        code:location.briefCode,
                        locale:"zhTW",
                        text: "墾丁的風景據點繁多，著名的有－海洋生物博物館，關山落日、龍鑾潭自然中心、後壁湖遊艇碼頭、南灣、白沙灣、墾丁森林遊樂區、社頂自然公園。墾丁青年活動中心、青蛙石、船帆石、砂島貝殼展示館、鵝鑾鼻燈塔、風吹沙、龍磐公園、佳樂水、港仔大沙漠、旭海大草原、龍坑及南仁山生態保護區......等，不勝枚舉。"
                ).save(flush:true);
            }
        }
        location = Location.findByLocationCode(CODE_SUN_MOON_LAKE)
        if(!location){
            location = new Location(
                    country:country,
                    locationCode:CODE_SUN_MOON_LAKE,
                    continentalCode:country.continentalCode,
                    worldSubRegionCode:country.worldSubRegionCode,
                    latitude: 23.865473,
                    longitude: 120.912099,searchField: "日月潭,Sun Moon Lake" ,
                    enable:true
            ).save(flush: true)
            if( !Localization.findByCodeAndLocale(location.nameCode,"*")){
                new Localization(
                        code:location.nameCode,
                        locale:"*",
                        text:"Sun-Moon Lake"
                ).save(flush:true);
            }

            if( !Localization.findByCodeAndLocale(location.nameCode,"zhTW")){
                def loc = new Localization(
                        code:location.nameCode,
                        locale:"zhTW",
                        text: "日月潭"
                ).save(flush:true);
            }
            if( !Localization.findByCodeAndLocale(location.briefCode,"*")){
                new Localization(
                        code:location.briefCode,
                        locale:"*",
                        text:"Nestled at 760 meters, this lake is famous for its clear, sparkling blue water set against a picturesque mountain backdrop. This is the largest lake in Taiwan and a traditional spot for newlywed couples to take their honeymoon. It has also been a center of aboriginal life for thousands of years, with aborignal people involved in its tourist industry since the 1930s."
                ).save(flush:true);
            }

            if( !Localization.findByCodeAndLocale(location.briefCode,"zhTW")){
                def loc = new Localization(
                        code:location.briefCode,
                        locale:"zhTW",
                        text: "日月潭國家風景區位於南投縣魚池鄉水社村，是全台最大的淡水湖泊，故日月潭有人稱為『海仔』或『水社海仔』；因潭景霧薄如紗，水波漣漣而得名為『水沙連』，四周群巒疊翠，海拔高748公尺，面積116平方公里，全潭以拉魯島(光華島)為界，南形如月弧，北形如日輪，所以改名為『日月潭』，潭面景像萬千，為出色的天然大湖，921大地震面臨前所未有的浩劫，帶來重建的契機，震後景點升格再現日月潭光華。"
                ).save(flush:true);
            }
        }
    }

    def createDefaultChannel(){
        String fontripChannelId = "com.fontrip.taiwan"
        if(!Channel.findByLegacyId(fontripChannelId)){
            def owner = User.findByUsername("admin@fontrip.com")
            Channel channelInstance = new Channel(owner: null,legacyId:fontripChannelId,status:Channel.STATUS_ENABLE).save(flush:true);
            new ChannelDetail(
                    name:"Fontrip Service",
                    poi:channelInstance,
                    lang:'*'
            ).save(flush:true);
            new ChannelDetail(
                    name:"寶島通服務平台",
                    poi:channelInstance,
                    lang:'zh_TW'
            ).save(flush:true);
        }
    }
}
