import fontrip.account.AdminRoleGroup
import fontrip.account.Role
import fontrip.account.RoleGroup
import fontrip.account.User
import fontrip.account.UserRole
import fontrip.account.UserRoleGroup
import fontrip.poi.Channel

class BootStrap {
    def systemService
    def fileService, fontripService
    def init = { servletContext ->
        //建立系統預設角色
        systemService.createDefaultRoleData()
        //檢查API Function
//        systemService.checkAccessFunction()
        //建立預設系統參數
        systemService.createSysConfig();
        //載入國家資料與台灣地區
//        if(fontrip.location.Country.count()==0){
//            systemService.loadCountry();
//            systemService.loadTaiwanCity();
//        }
        //處理自訂的Location
//        systemService.processLocations()

        //建立預設使用者
        createInitUser();

        //初始化檔案服務
        fileService.init();
        //載入預設圖片
        systemService.setFontripDefaultPhoto();

        //建立寶島通專用的通路
        println "------0--------"
        systemService.createDefaultChannel();
        println "------1--------"
        //20160912 by become
        for(channel in Channel.list()){
            fontripService.setupChannel(channel)
        }
        println "------2--------"
    }
    def createInitUser(){

        //create admin user
        def userList = ["admin@fontrip.com","admin-api@fontrip.com","admin-config@fontrip.com","doublekai0904@gmail.com"]
        for(username in userList){
            //1. 建立帳號
            def userInstance = User.findByUsername(username) ?: new User(
                    username: username,
                    password: '02750963',
                    email: 'doublekai0904@gmail.com',
                    name:username,backendUser:true,emailValidate:true,
                    nationality: 'TW',
                    enabled: true,infoCompleted: true).save(flush: true, failOnError: true)

            //2. 綁定 Role Group
            if (!userInstance.roleGroup.contains(RoleGroup.findByName("GROUP_USER"))) {
                UserRoleGroup.create(userInstance, RoleGroup.findByName("GROUP_USER"),true)
            }

            if(username=="admin@fontrip.com" || username=="doublekai0904@gmail.com"){
                UserRoleGroup.create(userInstance, AdminRoleGroup.findByName("GROUP_ADMIN_ACCOUNT_MANAGER"),true)
                UserRoleGroup.create(userInstance, AdminRoleGroup.findByName("GROUP_ADMIN_FINANCIAL_MANAGER"),true)
            }
            if(username=="admin-api@fontrip.com"){
                UserRoleGroup.create(userInstance, AdminRoleGroup.findByName("GROUP_ADMIN_API_MANAGER"),true)
            }
            if(username=="admin-config@fontrip.com"){
                UserRoleGroup.create(userInstance, AdminRoleGroup.findByName("GROUP_ADMIN_SYS_CONFIG"),true)
            }

        }

        // create SystemJobExecute and test user
        userList = ["SystemJobExecutor@fontrip.com","user@fontrip.com.tw"]
        for(username in userList){
            //1. 建立帳號
            def userInstance = User.findByUsername(username) ?: new User(
                    username: username,
                    password: '0275096342760988',
                    email: 'fontrip.tw+travelto@gmail.com',
                    name:username,backendUser:false,emailValidate:true,
                    nationality: 'TW',
                    enabled: true,infoCompleted: true).save(flush: true, failOnError: true)

            //2. 綁定 Role Group
            if (!userInstance.roleGroup.contains(RoleGroup.findByName("GROUP_USER"))) {
                UserRoleGroup.create(userInstance, RoleGroup.findByName("GROUP_USER"),true)
            }
        }
    }

    def destroy = {
    }
}
