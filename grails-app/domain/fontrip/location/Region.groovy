package fontrip.location

import fontrip.poi.Poi
import net.sf.json.JSONArray
import net.sf.json.JSONObject
import org.apache.commons.lang.LocaleUtils
import org.grails.plugins.localization.Localization

/**
 * Region
 * A domain class describes the data object and it's mapping to the database
 */
class Region extends Geography implements Serializable{

    final static String NAMECODE_PREFIX = "region.name.code."
    String regionCode
    Country country
    Poi poi

    static mapping = {
    }

    static constraints = {
        regionCode(unique:true,nullable:false)
        country(nullable:false)
        searchField(nullable:true)
        poi(nullable:true)
    }

    String getName(String lang){
        if(!lang) lang="*"
        return Localization.getMessage(nameCode,lang)
    }

    public String getNameCode(){
        return NAMECODE_PREFIX+regionCode;
    }
}
