package fontrip

import fontrip.account.MessageBox
import fontrip.account.MessageBoxContent
import fontrip.account.User
import grails.transaction.Transactional
import grails.util.Holders
import org.springframework.context.i18n.LocaleContextHolder

@Transactional
class MessageBoxService {
    def messageSource
    def mailService

    //[建立訊息] 由sender 發訊息給 特定使用者清單 ,是否同步發送email
    boolean sendMessageByUser(User sender , String subject , String content , String category ,  List<User> receiverList  , boolean isBackEndMsg = true , String url = null , boolean emailCopy = true){

        boolean result = true
        try{
            if(receiverList==null||receiverList.size()<1) return false

            //def ADMIN_USER = User.findByUsername('admin@fontrip.com')
            //if (sender==null) sender = ADMIN_USER

            // 1. 依序建立MessageBox
            for (receiverInstance in receiverList){
                MessageBox messageBoxInstance = new MessageBox(
                        subject: subject,
                        category: category,
                        name: receiverInstance.name,
                        email: receiverInstance.email,
                        phone: receiverInstance.mobilePhone,
                        isBackEndMsg: isBackEndMsg
                ).save(flush: true ,failOnError: true)

                MessageBoxContent messageContentInstance = new MessageBoxContent(
                        sender: sender,
                        senderName: sender?sender.username:"系統通知",
                        receiver: receiverInstance,
                        receiverName: receiverInstance.username,
                        content: content,
                        url: url,
                        messageBox: messageBoxInstance
                ).save(flush: true,failOnError: true)
                //檢查是否 送Email Copy
                if(emailCopy==true){
                    if(url!=null) content = content + "<br/>" + url
                    if(receiverInstance.email!=null && receiverInstance.email.indexOf('@')>0)
                        sendEmail(receiverInstance.email,subject,content)
                }
            }
        }
        catch (Exception ex){
            result = false
            println "sendMessageByUser Service : Send Message Exception :" + ex.message
        }
        finally {
            return result
        }
    }

    def sendEmail(String mailList,String mainSubject,String mailContent,boolean multiPart=false,String attachmentPath=null,String fileName=null){
        def senderTitle = getSenderTitle()
        def config = Holders.config
        String mailServerHost = config.fontrip.mail.host
        def mailServerStatus = hostServerAvailabilityCheck(mailServerHost,587)
        if(mailServerStatus==true){
            if(multiPart==true&& attachmentPath!=null){
                mailService.sendMail {
                    async true
                    multipart true
                    bcc mailList
                    from senderTitle
                    subject mainSubject
                    html mailContent
                    attachBytes fileName, " application/pdf", new File(attachmentPath).bytes
                }
            }else {
                mailService.sendMail {
                    async true
                    bcc mailList
                    from senderTitle
                    subject mainSubject
                    html mailContent
                }
            }
        }
        else
            println "mail server 狀態異常。"

    }

    def getSenderTitle(){
        def senderTitle= messageSource.getMessage('default.welcome.title',null,LocaleContextHolder.locale)
        def config = Holders.config
        //於標題加註 使用的環境
        String envMode = config.fontrip.envMode;
        if(envMode=="localMode"){
            senderTitle = "本機端_" + senderTitle
        }
        else if(envMode=="developerMode"){
            senderTitle = "測試環境_" + senderTitle
        }
        senderTitle = senderTitle + "<" + config.grails.mail.username + ">"
        return senderTitle
    }

    boolean hostServerAvailabilityCheck(String host,int port){
        boolean available = true;
        def sAdress = new InetSocketAddress(host, port);
        def socket = new Socket()
        try {
            socket.connect(sAdress,1000);
        }
        catch (UnknownHostException e)
        { // unknown host
            available = false;

        } catch (IOException e) {
            // io exception, service probably not running
            available = false;
        }
        finally {
            if(socket!=null)
                socket.close()
            return available;
        }

    }
}
