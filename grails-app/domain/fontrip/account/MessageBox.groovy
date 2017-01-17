package fontrip.account

class MessageBox {

    transient stringService

    final static String MESSAGE_CATEGORY_USER = "User"
    final static String MESSAGE_CATEGORY_SALEITEM = "SaleItem"
    final static String MESSAGE_CATEGORY_PURCHASE = "Purchase"
    final static String MESSAGE_CATEGORY_SERVICE = "Service"
    final static String MESSAGE_CATEGORY_SYSTEM = "System"
    final static String MESSAGE_CATEGORY_OTHER = "Other"


    /* Default (injected) attributes of GORM */
    Long id
    Long version

    /* Automatic timestamping of GORM */
    Date dateCreated

    //訊息標題
    String subject

    //訊息分類
    String category = MESSAGE_CATEGORY_SYSTEM

    //是否已回覆(僅供客服使用，找出尚未回覆的訊息)
    Boolean replied = false

    // 記錄聯絡資訊，用以支援使用者的意見回饋功能
    String name
    String email
    String phone

    Boolean isBackEndMsg = true


//	static	belongsTo	= []	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
//	static	hasOne		= []	// tells GORM to associate another domain object as an owner in a 1-1 mapping
    static	hasMany		= [messageBoxContents:MessageBoxContent]	// tells GORM to associate other domain objects for a 1-n or n-m mapping
//	static	mappedBy	= []	// specifies which property should be used in a mapping

    static mapping = {
    }

    static constraints = {
        name nullable: true
        email nullable: true
        phone nullable: true
    }
}
