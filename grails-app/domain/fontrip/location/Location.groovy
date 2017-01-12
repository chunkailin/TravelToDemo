package fontrip.location

import org.grails.plugins.localization.Localization

class Location extends Geography implements Serializable {

    final static String NAMECODE_PREFIX = "location.name.code."
    final static String BRIEFCODE_PREFIX = "location.brief.code."

    String locationCode

    Country country
    int continentalCode = 0
    int worldSubRegionCode = 0

    static mapping = {
    }

    static constraints = {
        locationCode(unique:true,nullable:false)
        country(nullable:false)

    }

    String getName(String lang){
        if(!lang) lang="*"
        return Localization.getMessage(nameCode,lang)
    }

    public String getNameCode(){
        return NAMECODE_PREFIX+locationCode;
    }
    public String getBriefCode(){
        return BRIEFCODE_PREFIX+locationCode;
    }
}
