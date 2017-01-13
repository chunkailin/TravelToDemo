package cklin

import fontrip.account.AdminRoleGroup
import fontrip.account.Role
import fontrip.account.RoleGroupRole


class TestController {
    def fileService
    def systemService
    def index() {
        systemService.setFontripDefaultPhoto()
        render "Secure access only"
    }
}
