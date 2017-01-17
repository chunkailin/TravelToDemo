package fontrip

import fontrip.account.AgentBranchRoleGroup
import fontrip.account.AgentRoleGroup
import fontrip.account.ChannelRoleGroup
import fontrip.account.MessageBox
import fontrip.account.Role
import fontrip.account.RoleGroupRole
import fontrip.account.StoreBranchRoleGroup
import fontrip.account.StoreRoleGroup
import fontrip.account.User
import fontrip.account.UserRoleGroup
import fontrip.poi.Agent
import fontrip.poi.AgentBranch
import fontrip.poi.AgentConfig
import fontrip.poi.Channel
import fontrip.poi.ChannelConfig
import fontrip.poi.Poi
import fontrip.poi.PoiDetail
import fontrip.poi.PoiUserMapping
import fontrip.poi.Store
import fontrip.poi.StoreBranch
import grails.transaction.Transactional
import grails.util.Environment
import org.springframework.context.i18n.LocaleContextHolder

@Transactional
class FontripService {
    def messageSource
    MessageBoxService messageBoxService
    static def mainPhotoName = (Environment.current.name in ['fontrip_demo','development_lion','production_lion'])?"FontripDefaultPhoto_Lion.jpg":"FontripDefaultPhoto.jpg"

    def setupChannel(Channel channelInstance){
        //1. 建立 Default MessageTemplate
        for(messageGroupStr in [MessageTemplate.Group_Customer]){
            def messageTypeList = MessageTemplate.getMessageTypeByMessageGroup(messageGroupStr)
            for(messageTypeStr in messageTypeList){
                def messageTemplateInstance = MessageTemplate.findByMessageTypeAndPoi(messageTypeStr,channelInstance)
                if(messageTemplateInstance==null){
                    messageTemplateInstance = new MessageTemplate(
                            messageGroup: messageGroupStr,
                            messageType : messageTypeStr,
                            poi         : channelInstance,
                            defaultTemplate : true).save()
                }
                def messageTemplateDetailInstance = MessageTemplateDetail.findByLangAndMessageTemplate('*',messageTemplateInstance)
                if(messageTemplateDetailInstance==null){
                    messageTemplateDetailInstance = new MessageTemplateDetail(
                            messageTemplate: messageTemplateInstance,
                            subject: MessageTemplateDetail.getDefaultSubjectByMessageTemplateType(messageTypeStr),
                            content: MessageTemplateDetail.getDefaultContentByMessageTemplateType(messageTypeStr)
                    ).save()
                }
            }
        }

        //2. Admin Manager (MAX Function)
        def channelRoleGroupAdmin = ChannelRoleGroup.findByNameAndChannel("GROUP_CHANNEL_ADMIN", channelInstance)
        if (!channelRoleGroupAdmin) {
            channelRoleGroupAdmin = new ChannelRoleGroup(name: "GROUP_CHANNEL_ADMIN", defaultRoleGroup: true, channel: channelInstance).save(flush: true)
        }

        def channelAdminRoleList = [
                "ROLE_CHANNEL_CATEGORY_MANAGEMENT", "ROLE_CHANNEL_NEWS_CATEGORY_MANAGEMENT", "ROLE_CHANNEL_TAG_MANAGEMENT",
                "ROLE_CHANNEL_NEWS_MANAGEMENT", "ROLE_CHANNEL_BANNERPANEL_MANAGEMENT",
                "ROLE_CHANNEL_BANNER_MANAGEMENT", "ROLE_CHANNEL_ANNOUNCEMENT_MANAGEMENT", "ROLE_CHANNEL_REGION_MANAGEMENT",
                "ROLE_CHANNEL_SALE_TOUR_MANAGEMENT", "ROLE_CHANNEL_EMAIL_CONFIG","ROLE_CHANNEL_MESSAGE_TEMPLATE_MANAGEMENT",
                "ROLE_CHANNEL_FINANCIAL_MANAGEMENT","ROLE_CHANNEL_CONFIG"
        ]
        for (role in channelAdminRoleList) {
            def roleObj = Role.findByAuthority(role)
            if (RoleGroupRole.countByRoleGroupAndRole(channelRoleGroupAdmin, roleObj) == 0) {
                RoleGroupRole.create(channelRoleGroupAdmin, roleObj, true)
            }
        }

        //3. Marketing Manager
        def channelRoleGroupMarketingManager = ChannelRoleGroup.findByNameAndChannel("GROUP_CHANNEL_MARKETING_MANAGER", channelInstance)
        if (!channelRoleGroupMarketingManager) {
            channelRoleGroupMarketingManager = new ChannelRoleGroup(name: "GROUP_CHANNEL_MARKETING_MANAGER", defaultRoleGroup: true, channel: channelInstance).save(flush: true)
        }
        def channelMarketingRoleList = [
                "ROLE_CHANNEL_BANNERPANEL_MANAGEMENT", "ROLE_CHANNEL_BANNER_MANAGEMENT"
        ]
        for (role in channelMarketingRoleList) {
            def roleObj = Role.findByAuthority(role)
            if (RoleGroupRole.countByRoleGroupAndRole(channelRoleGroupMarketingManager, roleObj) == 0) {
                RoleGroupRole.create(channelRoleGroupMarketingManager, roleObj, true)
            }
        }

        //4. Create Default Channel Config
        //產生設定檔
        def systemLocale = SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_GENERAL, SysConfig.CONFIG_CODE_SYSTEM_GENERAL_LOCALE)
        if (!ChannelConfig.findByConfigCodeAndChannel(SysConfig.CONFIG_CODE_CHANNEL_GENERAL_LOCALE, channelInstance)) {
            new AgentConfig(
                    configGroup: SysConfig.CONFIG_GROUP_CHANNEL_GENERAL,
                    configCode: SysConfig.CONFIG_CODE_CHANNEL_GENERAL_LOCALE,
                    scope: SysConfig.CONFIG_SCOPE_CHANNEL,
                    required: true,
                    editByAdmin: true,
                    editByAgent: true,
                    regularExpression: '^([a-z]{2}_[A-Z]{2},){1,}([a-z]{2}_[A-Z]{2}$)',
                    configValue: systemLocale ? systemLocale.configValue : 'zh_TW,zh_CN,en_US,ja_JP',
                    channel: channelInstance
            ).save(flush: true);
        }
        def defaultChannelConfigList = [
                SysConfig.CONFIG_CODE_CHANNEL_WEB_INDEX_URL,
                SysConfig.CONFIG_CODE_CHANNEL_WEB_REGISTER_SUCCESS_URL,
                SysConfig.CONFIG_CODE_CHANNEL_APP_REGISTER_SUCCESS_URL,
                SysConfig.CONFIG_CODE_CHANNEL_WEB_PAID_FAILED_URL,
                SysConfig.CONFIG_CODE_CHANNEL_WEB_PAID_SUCCESS_URL,
                SysConfig.CONFIG_CODE_CHANNEL_WEB_SALE_ITEM_URL,
                SysConfig.CONFIG_CODE_CHANNEL_WEB_USER_PROFILE_URL
        ]
        for (defaultConfigCode in defaultChannelConfigList) {
            def defaultConfigInstance = ChannelConfig.findByChannelAndConfigCode(channelInstance,defaultConfigCode)
            if(!defaultConfigInstance){
                defaultConfigInstance = new ChannelConfig(
                        channel: channelInstance,
                        scope:SysConfig.CONFIG_SCOPE_CHANNEL,
                        regularExpression:"^([a-z0-9])",
                        configGroup: SysConfig.CONFIG_GROUP_CHANNEL_GENERAL,
                        configCode: defaultConfigCode,
                        editByChannel : true
                ).save(flush: true,failOnError: true)
            }
        }
    }

    def changePoiOwner(Poi poiInstance, User newOwner) {
        if (poiInstance.owner != newOwner) {
            removePoiUser(poiInstance, poiInstance.owner)
        }
        // 2. 給予新owner預設權限
        def roleGroup
        if (poiInstance.class == Agent)
            roleGroup = AgentRoleGroup.findByNameAndAgent("GROUP_AGENT_ADMIN", poiInstance)
        else if (poiInstance.class == AgentBranch)
            roleGroup = AgentBranchRoleGroup.findByNameAndAgentBranch("GROUP_AGENT_BRANCH_ADMIN", poiInstance)
        else if (poiInstance.class == Store)
            roleGroup = StoreRoleGroup.findByNameAndStore("GROUP_STORE_ADMIN", poiInstance)
        else if (poiInstance.class == StoreBranch)
            roleGroup = StoreBranchRoleGroup.findByNameAndStoreBranch("GROUP_STORE_BRANCH_ADMIN", poiInstance)
        else if (poiInstance.class == Channel)
            roleGroup = ChannelRoleGroup.findByNameAndChannel("GROUP_CHANNEL_ADMIN", poiInstance)

        UserRoleGroup.create(newOwner, roleGroup, true)
        PoiUserMapping.create(poiInstance, newOwner, true)

        poiInstance.owner = newOwner
        poiInstance.save(flush: true)

        //新增的時候直接getName會出錯
        def poiDetailInstance = PoiDetail.findByPoiAndLang(poiInstance, '*')
        String title = messageSource.getMessage("mail.change.owner.title", [poiDetailInstance?.name, poiInstance.accountCode] as Object[], LocaleContextHolder.locale)
        String content = messageSource.getMessage("mail.change.owner.content", [poiDetailInstance?.name, poiInstance.accountCode] as Object[], LocaleContextHolder.locale)
        messageBoxService.sendMessageByUser(null, title, content, MessageBox.MESSAGE_CATEGORY_SYSTEM, [newOwner])
    }

    def removePoiUser(Poi poiInstance, User user) {
        def userRoleGroupList
        if (poiInstance.class == Agent)
            userRoleGroupList = UserRoleGroup.findAllByRoleGroupInListAndUser(AgentRoleGroup.findAllByAgent((Agent) poiInstance), user)
        else if (poiInstance.class == AgentBranch)
            userRoleGroupList = UserRoleGroup.findAllByRoleGroupInListAndUser(AgentBranchRoleGroup.findAllByAgentBranch((AgentBranch) poiInstance), user)
        else if (poiInstance.class == Store)
            userRoleGroupList = UserRoleGroup.findAllByRoleGroupInListAndUser(StoreRoleGroup.findAllByStore((Store) poiInstance), user)
        else if (poiInstance.class == StoreBranch)
            userRoleGroupList = UserRoleGroup.findAllByRoleGroupInListAndUser(StoreBranchRoleGroup.findAllByStoreBranch((StoreBranch) poiInstance), user)
        else if (poiInstance.class == Channel)
            userRoleGroupList = UserRoleGroup.findAllByRoleGroupInListAndUser(ChannelRoleGroup.findAllByChannel((Channel) poiInstance), user)
        for (userRoleGroup in userRoleGroupList) {
            userRoleGroup.delete(flush: true)
        }
        PoiUserMapping.remove(poiInstance, user, true)
    }
}
