package fontrip

import fontrip.account.AdminRoleGroup
import fontrip.account.Role
import fontrip.account.RoleGroupRole
import groovy.json.JsonSlurper


class SystemService {

    def createDefaultRoleData(){
        //create admin role list
        println("======Load Role List======")
        long startTime = (new Date()).getTime();
        InputStreamReader isr
        if(new File("grails-app/conf/systemRoleList.json").exists()){
            isr= new InputStreamReader(new FileInputStream("grails-app/conf/systemRoleList.json"), "UTF-8");
        }else{
            isr= new InputStreamReader(new FileInputStream("/home/project/FonTripDevelopment/grails-app/conf/systemRoleList.json"), "UTF-8");
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
    }
}
