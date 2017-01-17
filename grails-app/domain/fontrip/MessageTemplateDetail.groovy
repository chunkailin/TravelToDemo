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

    public static String getDefaultSubjectByMessageTemplateType(String messageTemplateStr){
        def result = ""
        if(messageTemplateStr==MessageTemplate.Type_User_Register){
            result = MessageTemplate.Parameter_Key_ChannelName + "註冊成功通知信"
        }
        else if(messageTemplateStr==MessageTemplate.Type_User_Validate){
            result = MessageTemplate.Parameter_Key_ChannelName + "帳號驗證通知信"
        }
        else if(messageTemplateStr==MessageTemplate.Type_User_Reset_Password){
            result = MessageTemplate.Parameter_Key_ChannelName + "密碼重設通知信"
        }
        else if(messageTemplateStr==MessageTemplate.Type_Payment_Confirm){
            result = MessageTemplate.Parameter_Key_ChannelName + "訂單成立通知信"
        }
        else if(messageTemplateStr==MessageTemplate.Type_Payment_Success){
            result = MessageTemplate.Parameter_Key_ChannelName + "付款成功通知信"
        }
        else if(messageTemplateStr==MessageTemplate.Type_Payment_Overdue){
            result = MessageTemplate.Parameter_Key_ChannelName + "逾期未付款通知信"
        }
        else if(messageTemplateStr==MessageTemplate.Type_Payment_Refund){
            result = MessageTemplate.Parameter_Key_ChannelName + "退貨成功通知信"
        }
    }

    public static String getDefaultContentByMessageTemplateType(String messageTemplateStr){
        def result = ""
        if(messageTemplateStr==MessageTemplate.Type_User_Register){
            result = "<p>"+MessageTemplate.Parameter_Key_UserLastName +"小姐/先生，您好：</p>" +
                    "<p>您已成功註冊"+MessageTemplate.Parameter_Key_ChannelName +"，歡迎您在"+MessageTemplate.Parameter_Key_ChannelName +"享受我們為會員提供的各項服務。</p>" +
                    "<p>有任何問題，歡迎來電洽詢"+MessageTemplate.Parameter_Key_ChannelName +"的客服專線(02)-XXXXXXXX。</p>" +
                    "<p>祝您購物愉快！</p>" +
                    "<p style=\"text-align: center;\">" + MessageTemplate.Parameter_Key_ChannelName +" 服務團隊 敬上</p>"
        }
        else if(messageTemplateStr==MessageTemplate.Type_User_Validate){
            result = "<p>"+MessageTemplate.Parameter_Key_UserLastName +"小姐/先生，您好：</p>" +
                    "<p>歡迎申請註冊" + MessageTemplate.Parameter_Key_ChannelName +"！請點選下方連結，以啟動帳號完成註冊程序。</p>" +
                    "<p>" + MessageTemplate.Parameter_Key_UserValidateLink +"</p>" +
                    "<p>提醒您：本驗證信連結1小時內有效，若您於收到本驗證信後1小時內沒有啟動帳號，則需再次申請註冊。</p>" +
                    "<p>有任何問題，歡迎來電洽詢" + MessageTemplate.Parameter_Key_ChannelName +"客服專線(02)-xxxxxxxx。</p>"+
                    "<p style=\"text-align: center;\">" + MessageTemplate.Parameter_Key_ChannelName +" 服務團隊 敬上</p>"
        }
        else if(messageTemplateStr==MessageTemplate.Type_User_Reset_Password){
            result = "<p>"+MessageTemplate.Parameter_Key_UserLastName +"小姐/先生，您好：</p>" +
                    "<p>我們收到了您重設" + MessageTemplate.Parameter_Key_ChannelName +"密碼的要求，請於1小時內點選下方連結修改您的密碼。</p>" +
                    "<p>" + MessageTemplate.Parameter_Key_UserResetPasswordLink +"</p>" +
                    "<p>如果您並未要求變更" + MessageTemplate.Parameter_Key_ChannelName +"密碼，請忽略此封信件。</p>" +
                    "<p>有任何問題，歡迎來電洽詢" + MessageTemplate.Parameter_Key_ChannelName +"客服專線(02)-xxxxxxxx。</p>"+
                    "<p style=\"text-align: center;\">" + MessageTemplate.Parameter_Key_ChannelName +" 服務團隊 敬上</p>"
        }
        else if(messageTemplateStr==MessageTemplate.Type_Payment_Confirm){
            result ="<p>"+MessageTemplate.Parameter_Key_UserLastName +"小姐/先生，您好：</p>" +
                    "<p>您於" + MessageTemplate.Parameter_Key_Date +"下了一筆訂單，請儘快完成付款以免造成權益損失。您的訂單資訊如下：</p>" +
                    "<ul>" +
                    "<li>訂單編號：" + MessageTemplate.Parameter_Key_PaymentOrderNo +"</li>" +
                    "<li>訂單日期：" + MessageTemplate.Parameter_Key_PaymentDate +"</li>" +
                    "<li>付款狀態：" + MessageTemplate.Parameter_Key_PaymentStatus +"</li>" +
                    "<li>付款方式：" + MessageTemplate.Parameter_Key_PaymentSettingName +"</li>" +
                    "<li>付款期限：" + MessageTemplate.Parameter_Key_PaymentDueDate +"</li>" +
                    "</ul>" +
                    "<p>您所購買的產品如下：</p>" +
                    "<p>" + MessageTemplate.Parameter_Key_PaymentDetail +"</p>" +
                    "<p>" + MessageTemplate.Parameter_Key_PaymentBookingOrder +"</p>"+
                    "<p>有任何問題，歡迎來電洽詢" + MessageTemplate.Parameter_Key_ChannelName +"客服專線(02)-xxxxxxxx。</p>"+
                    "<p style=\"text-align: center;\">" + MessageTemplate.Parameter_Key_ChannelName +" 服務團隊 敬上</p>"
        }
        else if(messageTemplateStr==MessageTemplate.Type_Payment_Success){
            result = "<p>"+MessageTemplate.Parameter_Key_UserLastName +"小姐/先生，您好：</p>" +
                    "<p>您已於" + MessageTemplate.Parameter_Key_PaymentPaidDate +"完成下列訂單付款。您的訂單資訊如下：</p>" +
                    "<ul>" +
                    "<li>訂單編號：" + MessageTemplate.Parameter_Key_PaymentOrderNo +"</li>" +
                    "<li>訂單日期：" + MessageTemplate.Parameter_Key_PaymentDate +"</li>" +
                    "<li>付款狀態：" + MessageTemplate.Parameter_Key_PaymentStatus +"</li>" +
                    "<li>付款方式：" + MessageTemplate.Parameter_Key_PaymentSettingName +"</li>" +
                    "<li>付款時間：" + MessageTemplate.Parameter_Key_PaymentPaidDate +"</li>" +
                    "</ul>" +
                    "<p>您所購買的產品如下：</p>" +
                    "<p>" + MessageTemplate.Parameter_Key_PaymentDetail +"</p>" +
                    "<p>" + MessageTemplate.Parameter_Key_PaymentBookingOrder +"</p>"+
                    "<p>有任何問題，歡迎來電洽詢" + MessageTemplate.Parameter_Key_ChannelName +"客服專線(02)-xxxxxxxx。</p>"+
                    "<p style=\"text-align: center;\">" + MessageTemplate.Parameter_Key_ChannelName +" 服務團隊 敬上</p>"
        }
        else if(messageTemplateStr==MessageTemplate.Type_Payment_Overdue){
            result = "<p>"+MessageTemplate.Parameter_Key_UserLastName +"小姐/先生，您好：</p>" +
                    "<p>您的訂單" + MessageTemplate.Parameter_Key_PaymentOrderNo +"因未於付款期限內付款，已被取消。若您仍對JourneyOn旅途中的產品有興趣，歡迎再次蒞臨選購！！</p>" +
                    "<p>您的訂單資訊如下：</p>" +
                    "<ul>" +
                    "<li>訂單編號：" + MessageTemplate.Parameter_Key_PaymentOrderNo +"</li>" +
                    "<li>訂單日期：" + MessageTemplate.Parameter_Key_PaymentDate +"</li>" +
                    "<li>付款狀態：" + MessageTemplate.Parameter_Key_PaymentStatus +"</li>" +
                    "<li>付款方式：" + MessageTemplate.Parameter_Key_PaymentSettingName +"</li>" +
                    "<li>付款期限：" + MessageTemplate.Parameter_Key_PaymentDueDate +"</li>" +
                    "</ul>" +
                    "<p>您所購買的產品如下：</p>" +
                    "<p>" + MessageTemplate.Parameter_Key_PaymentDetail +"</p>" +
                    "<p>有任何問題，歡迎來電洽詢" + MessageTemplate.Parameter_Key_ChannelName +"客服專線(02)-xxxxxxxx。</p>"+
                    "<p style=\"text-align: center;\">" + MessageTemplate.Parameter_Key_ChannelName +" 服務團隊 敬上</p>"
        }
        else if(messageTemplateStr==MessageTemplate.Type_Payment_Refund){
            result = "<p>"+MessageTemplate.Parameter_Key_UserLastName +"小姐/先生，您好：</p>" +
                    "<p>您已於" + MessageTemplate.Parameter_Key_Date +"完成訂單" + MessageTemplate.Parameter_Key_PaymentOrderNo +"的退貨作業。</p>" +
                    "<p>相關退貨資訊如下：</p>" +
                    "<p>" + MessageTemplate.Parameter_Key_PaymentRefundDetail +"</p>" +
                    "<p>有任何問題，歡迎來電洽詢" + MessageTemplate.Parameter_Key_ChannelName +"客服專線(02)-xxxxxxxx。</p>"+
                    "<p style=\"text-align: center;\">" + MessageTemplate.Parameter_Key_ChannelName +" 服務團隊 敬上</p>"
        }
    }
}
