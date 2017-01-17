package fontrip

import fontrip.poi.Poi

class MessageTemplate {
    transient stringService
    transient springSecurityService
    //final static String STATUS_INIT = "I"

    //Message Group
    final static String Group_Customer = "Group_Customer"
    final static String Group_Contract = "Group_Contract"

    //Message Type
    final static String Type_User_Validate       = "Type_User_Validate"
    final static String Type_User_Register       = "Type_User_Register"
    final static String Type_User_Reset_Password = "Type_User_Reset_Password"
    final static String Type_Payment_Confirm     = "Type_Payment_Confirm"
    final static String Type_Payment_Success     = "Type_Payment_Success"
    final static String Type_Payment_Overdue     = "Type_Payment_Overdue"
    final static String Type_Payment_Refund      = "Type_Payment_Refund"

    /* Default (injected) attributes of GORM */
    Long id
    Long version

    /* Automatic timestamping of GORM */
    Date dateCreated
    Date lastUpdated

    String messageGroup
    String messageType
    Poi poi
    boolean defaultTemplate = false

    static transients = ['details', 'stringService']
    Map details

    def onLoad(){
        loadDetail()
    }

    protected void loadDetail(){
        def detailList = MessageTemplateDetail.findAllByMessageTemplate(this)
        details = new HashMap()
        for(detail in detailList){
            details.put(details.lang, detail)
        }
    }

    MessageTemplateDetail detail(String lang = '*'){
        def tempDetail = null
        if(details){
            tempDetail = details.get(lang)?:details.get('*')
        }
        return tempDetail
    }

    static mapping = {
        //id generator: 'uuid'

    }

    static constraints = {
        poi(nullable: true)
    }

    public String getHashid(){
        return  stringService.encodeHashid(MessageTemplate, id)
    }

    public static List<String> getMessageTypeByMessageGroup(String messageGroupStr){
        def result = []
        if(messageGroupStr==Group_Customer) {
            result = [
                    Type_User_Validate, Type_User_Register, Type_User_Reset_Password,
                    Type_Payment_Confirm, Type_Payment_Success, Type_Payment_Overdue, Type_Payment_Refund
            ]
        }
        else if(messageGroupStr==Group_Contract){

        }
        return result
    }


    // Parameter Keys
    final static String Parameter_Key_Date   = "[[date]]"
    final static String Parameter_Key_ChannelName   = "[[channelName]]"

    final static String Parameter_Key_UserFirstName         = "[[userFirstName]]"
    final static String Parameter_Key_UserLastName          = "[[userLastName]]"
    final static String Parameter_Key_UserValidateLink      = "[[userValidateLink]]"
    final static String Parameter_Key_UserResetPasswordLink = "[[userResetPasswordLink]]"

    final static String Parameter_Key_PaymentOrderNo        = "[[paymentOrderNo]]"
    final static String Parameter_Key_PaymentDate           = "[[paymentDate]]"
    final static String Parameter_Key_PaymentStatus         = "[[paymentStatus]]"
    final static String Parameter_Key_PaymentSettingName    = "[[paymentSettingName]]"
    final static String Parameter_Key_PaymentPaidDate       = "[[paymentPaidDate]]"
    final static String Parameter_Key_PaymentDueDate        = "[[paymentDueDate]]"
    final static String Parameter_Key_PaymentDetail         = "[[paymentDetail]]"
    final static String Parameter_Key_PaymentBookingOrder   = "[[paymentBookingOrder]]"
    final static String Parameter_Key_PaymentRefundDetail  = "[[paymentRefundDetail]]"
}
