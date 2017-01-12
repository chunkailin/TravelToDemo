import fontrip.account.Role
import fontrip.account.User
import fontrip.account.UserRole

class BootStrap {
    def systemService
    def init = { servletContext ->
        //建立系統預設角色
        systemService.createDefaultRoleData()
    }
    def destroy = {
    }
}
