package fontrip

class MessageTemplateDetail {

    transient stringService

    //final static String STATUS_INIT = "I"

    /* Default (injected) attributes of GORM */
    Long id
    Long version

    /* Automatic timestamping of GORM */
    Date dateCreated
    Date lastUpdated
    String lang='*'

    String subject = ""
    String content = ""


    //List attachmentFiles = new ArrayList()


    //static auditable = [ignore:['version','lastUpdated']]
    static	belongsTo	= [messageTemplate:MessageTemplate]	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
    ///static hasMany		= [attachmentFiles:SysFile]

    static mapping = {
        content type: 'text'
    }

    static constraints = {
        content (nullable: true)
    }

    public String getHashid(){
        return stringService.encodeHashid(MessageTemplateDetail,id)
    }
}
