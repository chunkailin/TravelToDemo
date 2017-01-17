package cklin

import fontrip.account.AdminRoleGroup
import fontrip.account.Role
import fontrip.account.RoleGroupRole
import fontrip.account.User
import fontrip.poi.PoiDetail
import org.springframework.context.i18n.LocaleContextHolder


class TestController {
    def fileService
    def systemService
    def messageSource
    def index() {
        systemService.setFontripDefaultPhoto()
        def adminInstance = User.findByName('admin@fontrip.com')
        String title = messageSource.getMessage("mail.change.owner.title", [adminInstance?.name, adminInstance.nationality] as Object[], LocaleContextHolder.locale)
        String content = messageSource.getMessage("mail.change.owner.content", [adminInstance?.name, adminInstance.nationality] as Object[], LocaleContextHolder.locale)
        println "title="+title + ", content="+content
        render "Secure access only"
    }
}
