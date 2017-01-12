package fontrip.location

import org.grails.plugins.localization.Localization

class Country extends Geography implements Serializable {

    String isoCodeAlpha2
    String isoCodeAlpha3
    int isoCodeNumeric = 0
    final static String NAMECODE_PREFIX = 'country.name.'

    int continentalCode = 0
    int worldSubRegionCode = 0
    String countryCode //電話國碼

    //用來過濾前端可以選擇的國籍
    boolean choosable = true

    static mapping = {
    }

    static constraints = {

        isoCodeNumeric(unique:true)
        isoCodeAlpha2(unique:true)
        isoCodeAlpha3(unique:true)
        countryCode(nullable: true)
    }
    String getName(String lang){
        if(!lang) lang ='*'
        return Localization.getMessage(nameCode,lang)
    }

    String getNameCode(){
        NAMECODE_PREFIX+this.isoCodeAlpha3
    }
}
