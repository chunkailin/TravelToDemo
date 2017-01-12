package fontrip.location

import fontrip.SysFile
import net.sf.json.JSONObject

abstract class Geography {

    transient stringService,messageSource
    static transients = ['stringService','messageSource']	//TODO 測試是否可以取得多國語系名稱

    /* Default (injected) attributes of GORM */
    Long id
    Long version

    /* Automatic timestamping of GORM */
    Date dateCreated
    Date lastUpdated
    Double latitude=0.0
    Double longitude=0.0
    List photos = new ArrayList()
    static hasMany		= [photos:SysFile]
    boolean enable = false

    String searchField
    static constraints = {

        searchField(nullable:true)

    }

//    abstract String getName(String lang)
//    String getName(){return getName('*')}
//    abstract String jsonTree(String lang, Poi poi)
//    public String jsonNode(String lang){
//        JSONObject obj = new JSONObject()
//        obj.put("class",this.class)
//        obj.put("parameterName",this.class.getSimpleName().toLowerCase()+"Id")
//        obj.put("id",this.hashid)
//        obj.put("name",getName(lang))
//        return obj.toString()
//    }
    public String getHashid(){
        return stringService.encodeHashid(Geography,id)
//        if(this.class==Geography)
//            return stringService.encodeHashid(Geography,id)
//        else if(this.class==Location)
//            return stringService.encodeHashid(Location,id)
//        else if(this.class==Country)
//            return stringService.encodeHashid(Country,id)
//        else if(this.class==Area)
//            return stringService.encodeHashid(Area,id)
//        else if(this.class==Region)
//            return stringService.encodeHashid(Region,id)
//        else
//            return null
    }
}
