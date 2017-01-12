package fontrip.location

import fontrip.poi.Poi
import org.apache.commons.lang.LocaleUtils

class Area extends Geography implements Serializable {

    final static String NAMECODE_PREFIX = "area.name.code."
    String areaCode

    Country country

    Poi poi

    static mapping = {
    }

    static constraints = {
        areaCode(unique:true,nullable:false)
        country(nullable:false)

        poi(nullable:true)
    }

    String getName(String lang){
        if(!lang) lang="*"
        return messageSource.getMessage(this.nameCode,null,LocaleUtils.toLocale(lang))
    }

    public String getNameCode(){
        return NAMECODE_PREFIX+areaCode;
    }
}
