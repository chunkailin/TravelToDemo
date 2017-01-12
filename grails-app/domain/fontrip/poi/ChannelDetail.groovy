package fontrip.poi

/**
 * ChannelDetail
 * A domain class describes the data object and it's mapping to the database
 */
class ChannelDetail extends PoiDetail{
    String emailFooter
    String webSiteTitle

    static constraints = {
        emailFooter(nullable:true)
        webSiteTitle(nullable:true)
    }
}