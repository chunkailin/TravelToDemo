package fontrip.account

/**
 * MessageBoxContent
 * A domain class describes the data object and it's mapping to the database
 */
class MessageBoxContent {

    /* Default (injected) attributes of GORM */
    Long id

    /* Automatic timestamping of GORM */
    Date dateCreated

    //若由系統發出的通知，則 sender 為 null
    //若由使用者發給系統的意見回饋，則 receiver 為 null
    User sender
    User receiver

    //當使用者將訊息刪除時，僅是將sender or receiver設為null, 除非兩者均為null,才真正的刪除
    //因此需額外記錄字串username 來正確顯示 收(寄)件人名稱
    String senderName
    String receiverName

    //訊息內容
    String content
    //獨立連結欄位，方便使用者直接點擊進入
    String url

    //是否已讀
    Boolean read = false

    static	belongsTo	= [messageBox:MessageBox]	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
//	static	hasOne		= []	// tells GORM to associate another domain object as an owner in a 1-1 mapping
//	static	hasMany		= []	// tells GORM to associate other domain objects for a 1-n or n-m mapping
//	static	mappedBy	= []	// specifies which property should be used in a mapping 

    static mapping = {
        //read為系統保留字
        read column: '`read`'
        version false
    }

    static constraints = {
        sender nullable: true
        receiver nullable: true
        content maxSize: 1000
        url nullable: true
    }

    /*
     * Methods of the Domain Class
     */
//	@Override	// Override toString for a nicer / more descriptive UI 
//	public String toString() {
//		return "${name}";
//	}
}
