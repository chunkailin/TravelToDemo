package fontrip.poi

import fontrip.location.Geography

class Channel extends Poi{
    String serverUrl
    final static String CHANNEL_MODE_B2B = "B2B"
    final static String CHANNEL_MODE_B2C = "B2C"
    String channelMode = CHANNEL_MODE_B2B
    final static String PATH_OAUTH_CHECK = "oauthCheck"
    final static String PATH_PAYMENT_SUCCESS_PATH = "/fontrip/payment?success=true&id="
    final static String PATH_PAYMENT_FAILED_PATH = "/fontrip/payment?success=false&id="

    String saleTourViewPath = "/saleTour/view/"
    String spotViewPath = "/spot/view/"
    String currency = "TWD"

    Geography rootGeography

    def beforeInsert() {
        def tmpAccountCode = 'CH'+stringService.generateRandomWord(8,true)
        while(Channel.findByAccountCode(tmpAccountCode) ){
            tmpAccountCode = 'CH'+stringService.generateRandomWord(8,true)
        }
        accountCode = tmpAccountCode
    }
    ChannelDetail detail(String lang=(Locale.default.toString())){
        def  tempDetail = null
        if(details){
            tempDetail = details.get(lang)?:details.get('*')
        }
        return tempDetail
    }
    static constraints = {
        spotViewPath(nullable:true)
        saleTourViewPath(nullable:true)
        serverUrl(nullable:true)
        rootGeography(nullable:true)
    }
}
