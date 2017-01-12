package fontrip

class SysConfig {

    transient stringService
    /* Default (injected) attributes of GORM */
    Long	id
    Long	version

    /* Automatic timestamping of GORM */
    Date	dateCreated
    Date	lastUpdated

    String configGroup
    String configCode
    String scope
    boolean editable = true
    boolean required = true
    String regularExpression

    String configValue
    boolean editByAdmin = true

    // [系統] 圖片上傳設定
    final static String CONFIG_GROUP_SYSTEM_FILEUPLOAD = "config.system.fileupload"
    final static String CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_UPLOADLIMIT = "config.system.fileupload.photo.uploadlimit"
    final static String CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_EXTENSION = "config.system.fileupload.photo.extension"
    final static String CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_DEFAULTSIZE = "config.system.fileupload.photo.defaultsize"
    final static String CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_AVAILABLESIZE = "config.system.fileupload.photo.availablesize"
    final static String CONFIG_CODE_SYSTEM_FILEUPLOAD_FILE_UPLOADLIMIT = "config.system.fileupload.file.uploadlimit"
    final static String CONFIG_CODE_SYSTEM_FILEUPLOAD_FILE_EXTENSION = "config.system.fileupload.file.extension"
    final static String CONFIG_CODE_SYSTEM_FILEUPLOAD_AUDIO_UPLOADLIMIT = "config.system.fileupload.audio.uploadlimit"
    final static String CONFIG_CODE_SYSTEM_FILEUPLOAD_AUDIO_EXTENSION = "config.system.fileupload.audio.extension"
    final static String CONFIG_CODE_SYSTEM_FILEUPLOAD_VIDEO_UPLOADLIMIT = "config.system.fileupload.video.uploadlimit"
    final static String CONFIG_CODE_SYSTEM_FILEUPLOAD_VIDEO_EXTENSION = "config.system.fileupload.video.extension"
    final static String CONFIG_CODE_SYSTEM_FILEUPLOAD_UPLOAD_PATH = "config.system.fileupload.upload.path"
    final static String CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_MIN_WIDTH = "config.system.fileupload.photo.minWidth"
    final static String CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_MIN_HEIGHT = "config.system.fileupload.photo.minHeight"

    // [系統] Cloud Log 設定
    final static String CONFIG_GROUP_SYSTEM_CLOUD_LOG = "config.system.cloud.log"
    final static String CONFIG_CODE_SYSTEM_CLOUD_LOG_APPID = "config.system.cloud.log.appid"
    final static String CONFIG_CODE_SYSTEM_CLOUD_LOG_SECRET = "config.system.cloud.log.secret"

    // [系統] 資料驗證
    final static String CONFIG_GROUP_SYSTEM_VALIDATE = "config.system.validate"
    final static String CONFIG_CODE_SYSTEM_VALIDATE_EMAIL_TIME = "config.system.validate.email.time"


    // [系統] 一般設定
    final static String CONFIG_GROUP_SYSTEM_GENERAL = "config.system.general"
    final static String CONFIG_CODE_SYSTEM_GENERAL_DEBUG = "config.system.general.debug"
    final static String CONFIG_CODE_SYSTEM_GENERAL_LOCALE = "config.system.general.locale"

    // [系統] 圖片 FTP 設定
    final static String CONFIG_GROUP_SYSTEM_FTP = "config.system.ftp"
    final static String CONFIG_CODE_SYSTEM_FTP_SERVER = "config.system.ftp.server"
    final static String CONFIG_CODE_SYSTEM_FTP_USERNAME = "config.system.ftp.username"
    final static String CONFIG_CODE_SYSTEM_FTP_PASSWORD = "config.system.ftp.password"
    final static String CONFIG_CODE_SYSTEM_FTP_BASEFOLDER = "config.system.ftp.baseFolder"


    // [系統] 第三方(Google) API Key 設定
    final static String CONFIG_GROUP_SYSTEM_GOOGLE_API = "config.system.google.api"
    final static String CONFIG_CODE_SYSTEM_GOOGLE_API_KEY = "config.system.google.api.key"
    final static String CONFIG_CODE_SYSTEM_GOOGLE_SERVER_API_KEY = "config.system.google.server.api.key"

    // [店家] 預約通知設定
    final static String CONFIG_GROUP_STORE_BOOKING_NOTIFY = "config.store.booking.notify"
    final static String CONFIG_CODE_STORE_BOOKING_NOTIFY_SMS_ENABLE = "config.store.booking.notify.sms.enable"
    final static String CONFIG_CODE_STORE_BOOKING_NOTIFY_SMS_NUMBER = "config.store.booking.notify.sms.number"
    final static String CONFIG_CODE_STORE_BOOKING_NOTIFY_FAX_ENABLE = "config.store.booking.notify.fax.enable"
    final static String CONFIG_CODE_STORE_BOOKING_NOTIFY_FAX_NUMBER = "config.store.booking.notify.fax.number"

    // [店家] 一般設定
    final static String CONFIG_GROUP_STORE_GENERAL = "config.store.general"
    final static String CONFIG_CODE_STORE_GENERAL_LOCALE = "config.store.general.locale"

    // [店家] unknown
    final static String CONFIG_GROUP_STORE_MODULE = "config.store.module"
    final static String CONFIG_CODE_STORE_MODULE_BOOKING = "config.store.module.booking"
    final static String CONFIG_CODE_STORE_MODULE_BRANCH = "config.store.module.branch"

    // [店家] 匯款帳戶設定
    final static String CONFIG_GROUP_STORE_BANK_ACCOUNT = "config.store.bank.account.group"
    final static String CONFIG_CODE_STORE_BANK_ACCOUNT_NAME = "config.store.bank.account.name"
    final static String CONFIG_CODE_STORE_BANK_ACCOUNT_BANKID = "config.store.bank.account.bank.id"
    final static String CONFIG_CODE_STORE_BANK_ACCOUNT_NUMBER = "config.store.bank.account.number"

    // [分店店家] 一般設定
    final static String CONFIG_GROUP_BRANCH_GENERAL = "config.branch.general"
    final static String CONFIG_CODE_BRANCH_GENERAL_LOCALE = "config.branch.general.locale"

    // [代理商] 一般設定
    final static String CONFIG_GROUP_AGENT_GENERAL = "config.agent.general"
    final static String CONFIG_CODE_AGENT_GENERAL_LOCALE = "config.agent.general.locale"

    // [通路商] 一般設定
    final static String CONFIG_GROUP_CHANNEL_GENERAL = "config.channel.general"
    final static String CONFIG_CODE_CHANNEL_GENERAL_LOCALE = "config.channel.general.locale"
    final static String CONFIG_CODE_CHANNEL_WEB_INDEX_URL = "config.channel.web.index.url"
    final static String CONFIG_CODE_CHANNEL_WEB_REGISTER_SUCCESS_URL = "config.channel.web.register.success.url"
    final static String CONFIG_CODE_CHANNEL_WEB_SALE_ITEM_URL = "config.channel.web.saleitem.url"
    final static String CONFIG_CODE_CHANNEL_WEB_USER_PROFILE_URL = "config.channel.web.user.profile.url"
    final static String CONFIG_CODE_CHANNEL_WEB_PAID_SUCCESS_URL = "config.channel.web.paid.success.url"
    final static String CONFIG_CODE_CHANNEL_WEB_PAID_FAILED_URL = "config.channel.web.paid.failed.url"
    final static String CONFIG_CODE_CHANNEL_APP_REGISTER_SUCCESS_URL = "config.channel.app.register.success.url"

    // [代理商] 串接雄獅系統設定
    final static String CONFIG_GROUP_AGENT_MODULE_LION = "config.agent.module.lion"
    final static String CONFIG_CODE_AGENT_MODULE_LION_KEY = "config.agent.module.lion.key"
    final static String CONFIG_CODE_AGENT_MODULE_LION_SECRET = "config.agent.module.lion.secret"
    final static String CONFIG_CODE_AGENT_MODULE_LION_CHANNEL = "config.agent.module.lion.channel"

    // [代理商] 串接雄獅電子票券
    final static String CONFIG_GROUP_AGENT_MODULE_LION_ELECTRONIC = "config.agent.module.lion.electronic"
    final static String CONFIG_CODE_AGENT_MODULE_LION_ELECTRONIC_KEY = "config.agent.module.lion.electronic.key"
    final static String CONFIG_CODE_AGENT_MODULE_LION_ELECTRONIC_SECRET = "config.agent.module.lion.electronic.secret"

    // [代理商] 串接17Life系統設定
    final static String CONFIG_GROUP_AGENT_MODULE_17LIFE = "config.agent.module.17Life"
    final static String CONFIG_CODE_AGENT_MODULE_17LIFE_KEY = "config.agent.module.17Life.key"
    final static String CONFIG_CODE_AGENT_MODULE_17LIFE_CHANNEL = "config.agent.module.17Life.channel"

    // [代理商] 串接THSR(高鐵)系統設定
    final static String CONFIG_GROUP_AGENT_MODULE_THSR = "config.agent.module.THSR"
    final static String CONFIG_CODE_AGENT_MODULE_THSR_AGENT_ID = "config.agent.module.THSR.agentId"

    // [代理商] 匯款帳戶設定
    final static String CONFIG_GROUP_AGENT_BANK_ACCOUNT = "config.agent.bank.account.group"
    final static String CONFIG_CODE_AGENT_BANK_ACCOUNT_NAME = "config.agent.bank.account.name"
    final static String CONFIG_CODE_AGENT_BANK_ACCOUNT_BANKID = "config.agent.bank.account.bank.id"
    final static String CONFIG_CODE_AGENT_BANK_ACCOUNT_NUMBER = "config.agent.bank.account.number"

    // [代理商] 信託參數設定
    final static String CONFIG_GROUP_AGENT_MODULE_TRUST = "config.agent.module.trust"
    final static String CONFIG_CODE_AGENT_MODULE_TRUST_PLATFORM_LEGACYID = "config.agent.module.trust.platform.legacyId"
    final static String CONFIG_CODE_AGENT_MODULE_TRUST_AGENT_LEGACYID = "config.agent.module.trust.agent.legacyId"

    // [合約] 串接外部系統設定
    final static String CONFIG_GROUP_CONTRACT_SETTING                     = "config.contract.setting"
    final static String CONFIG_CODE_CONTRACT_SETTING_ETID_LION = "config.contract.setting.etid.lion"       //雄獅系統中的票券編號
    final static String CONFIG_CODE_CONTRACT_SETTING_ESEQ_LION = "config.contract.setting.eseq.lion"       //雄獅系統中的品項編號
    final static String CONFIG_CODE_CONTRACT_SETTING_DEALGUID_17LIFE = "config.contract.setting.dealGuid.17Life"     //17Life系統中的商品編號
    final static String CONFIG_CODE_CONTRACT_SETTING_STOREGUID_17LIFE      = "config.contract.setting.storeGuid.17Life"     //17Life系統中的商品編號
    final static String CONFIG_CODE_CONTRACT_SETTING_PARENT_DEALGUID_17LIFE      = "config.contract.setting.parent.dealGuid.17Life"     //17Life系統中的商品Parent編號
    final static String CONFIG_CODE_CONTRACT_SETTING_GROUP_ID      = "config.contract.setting.group.id"     //雄獅團單編號



    // [套票] 一般設定
    final static String CONFIG_GROUP_PACKAGETOUR_SETTING                    = "config.packageTour.setting"
    final static String CONFIG_CODE_PACKAGETOUR_SETTING_LANGUAGE            = "config.packageTour.setting.language"        //服務語言
    final static String CONFIG_CODE_PACKAGETOUR_SETTING_ENOUGHREGISTER      = "config.packageTour.setting.enoughRegister"  //成團人數
    final static String CONFIG_CODE_PACKAGETOUR_SETTING_DURATION            = "config.packageTour.setting.duration"        //遊程時間
    final static String CONFIG_CODE_PACKAGETOUR_SETTING_REFUNDRULE          = "config.packageTour.setting.refundRule"      //退改規定
    final static String CONFIG_CODE_PACKAGETOUR_SETTING_TRANSPORTATION      = "config.packageTour.setting.transportation"  //交通方式
    final static String CONFIG_CODE_PACKAGETOUR_SETTING_DEPARTUREDAY        = "config.packageTour.setting.departureDay"    //出發星期
    final static String CONFIG_CODE_PACKAGETOUR_SETTING_TRANSPORTATION_OWNTO        = "config.packageTour.setting.transportation.ownTo"  //交通方式-自行前往
    final static String CONFIG_CODE_PACKAGETOUR_SETTING_TRANSPORTATION_SHUTTLE      = "config.packageTour.setting.transportation.shuttle"  //交通方式-接送


    final static String CONFIG_SCOPE_SYSTEM = "SYSTEM"
    final static String CONFIG_SCOPE_STORE = "STORE"
    final static String CONFIG_SCOPE_CHANNEL = "CHANNEL"
    final static String CONFIG_SCOPE_AGENT = "AGENT"
    final static String CONFIG_SCOPE_BRANCH = "BRANCH"
    final static String CONFIG_SCOPE_USER = "USER"
    final static String CONFIG_SCOPE_SPOT = "SPOT"
    final static String CONFIG_SCOPE_CONTRACT = "CONTRACT"
    final static String CONFIG_SCOPE_PACKAGETOUR = "PACKAGETOUR"

    public static transient configScopeList = [
            CONFIG_SCOPE_SYSTEM,
            CONFIG_SCOPE_SPOT,
            CONFIG_SCOPE_CHANNEL,
            CONFIG_SCOPE_AGENT,
            CONFIG_SCOPE_BRANCH,
            CONFIG_SCOPE_USER,
            CONFIG_SCOPE_SPOT,
            CONFIG_SCOPE_CONTRACT,
            CONFIG_SCOPE_PACKAGETOUR
    ]
    static auditable = [ignore:['version','lastUpdated']]
//	static	belongsTo	= []	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
//	static	hasOne		= []	// tells GORM to associate another domain object as an owner in a 1-1 mapping
//	static	hasMany		= []	// tells GORM to associate other domain objects for a 1-n or n-m mapping
//	static	mappedBy	= []	// specifies which property should be used in a mapping

    static	mapping = {

    }
    static transients = ['stringService']
    static	constraints = {
        configValue (nullable:true)
        regularExpression (nullable:true)
    }
    static String getConfig(code){
        return SysConfig.findByConfigCodeAndScope(code,CONFIG_SCOPE_SYSTEM)?SysConfig.findByConfigCodeAndScope(code,CONFIG_SCOPE_SYSTEM).configValue:'';

    }

    public String getHashid(){
        return stringService.encodeHashid(SysConfig,id)
    }

}
