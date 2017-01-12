package fontrip.poi

import fontrip.SysFile
import fontrip.account.User
import fontrip.location.Area
import fontrip.location.Country
import fontrip.location.Location

abstract class Poi implements Serializable {

    transient stringService
    /* Default (injected) attributes of GORM */
    Long	id
    Long	version

    /* Automatic timestamping of GORM */
    Date	dateCreated
    Date	lastUpdated

    String accountCode = "XX00000000";//帳號代碼,新增時自動產生10位數字

    SysFile mainPhoto

    String vatNumber

    String status = STATUS_EDIT
    final static String STATUS_EDIT = "EDIT"
    final static String STATUS_ENABLE = "ENABLE"
    final static String STATUS_DISABLE = "DISABLE"

    Double latitude = 25.0366873
    Double longitude = 121.4705683
    Integer viewCount=0;

    String legacyId


    Country country
    Location location
    Area area
    String phoneNumber
    String faxNumber
    String openTime
    String googlePlaceId

    final static String DATA_SOURCE_FONTRIP = "FONTRIP"
    final static String DATA_SOURCE_OPENDATA = "OPENDATA"
    final static String DATA_SOURCE_TRIPRESSO = "TRIPRESSO"

    final static String COOPERATION_MODE_PRIVATE = "PRIVATE"
    final static String COOPERATION_MODE_PROTECT= "PROTECT"
    final static String COOPERATION_MODE_PUBLIC = "PUBLIC"



    String dataFrom = DATA_SOURCE_FONTRIP
    String cooperationMode = COOPERATION_MODE_PUBLIC

    static transients = ['stringService','details']
//    static hasMany		= [tags:SysTag, poiCategoryList:PoiCategoryMapping]	// tells GORM to associate other domain objects for a 1-n or n-m mapping

    User owner

    Map details

    boolean searchable = true //用來前端判斷搜尋的資料,Store/Spot纔可以被搜尋

    String keywordIndex

    User creator
    int rating = 0

    PoiDetail detail(String lang='*'){
        def  tempDetail = null
        if(details){
            tempDetail = details.get(lang)?:details.get('*')
        }
        return tempDetail
    }
    public void loadDetail(){
        println "---loadDetail...."
        def detailList = PoiDetail.findAllByPoi(this)
        details = new HashMap()
        for(detail in  detailList){
            details.put(detail.lang,detail)
        }
        println "---loadDetail Done...."
    }

    static mapping = {
        keywordIndex  type: 'text'
        openTime type: 'text'
    }

    static constraints = {
        vatNumber(nullable:true)
        mainPhoto(nullable:true)
        legacyId(nullable:true)
        country(nullable:true)
        location(nullable:true)
        area(nullable:true)
        phoneNumber(nullable: true)
        faxNumber(nullable: true)
        openTime(nullable:true)
        accountCode(nullable:false,unique:true)
        owner(nullable:true)
        keywordIndex(nullable:true)
        creator(nullable:true)
        dataFrom(nullable:true)
        googlePlaceId(nullable:true)
    }

    public boolean isEnable(){
        return (status ==STATUS_ENABLE)
    }
    public setEnable(boolean enable){
        if(enable){
            status = STATUS_ENABLE
        }else{
            status = STATUS_DISABLE
        }
    }

    public String getName(String locale = '*'){
        return this.detail(locale)?.name;
    }

    public String getDescription(String locale = '*'){
        return this.detail(locale)?.description
    }

    public String getHashid(){
        if(this.class==Store)
            return stringService.encodeHashid(Store,id)
        else if(this.class==Agent)
            return stringService.encodeHashid(Agent,id)
        else if(this.class==StoreBranch)
            return stringService.encodeHashid(StoreBranch,id)
        else if(this.class==AgentBranch)
            return stringService.encodeHashid(AgentBranch,id)
        else if(this.class==Spot)
            return stringService.encodeHashid(Spot,id)
        else if(this.class==Channel)
            return stringService.encodeHashid(Channel,id)
        else
            return null
    }

    def onLoad(){
        viewCount ++;
        loadDetail()
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Poi poi = (Poi) o

        if (id != poi.id) return false
        if (legacyId != poi.legacyId) return false

        return true
    }

    int hashCode() {
        int result
        result = id.hashCode()
        result = 31 * result + (legacyId != null ? legacyId.hashCode() : 0)
        return result
    }

    def beforeUpdate(){
        if(this.class!=Channel && (this.openTime==null || this.openTime == "")){
            this.openTime = "{ \"standard\":[ { \"dayOfWeek\":1, \"timeList\":[ [ \"09:30-17:00\" ] ] }, { \"dayOfWeek\":2, \"timeList\":[ [ \"09:30-17:00\" ] ] }, { \"dayOfWeek\":3, \"timeList\":[ [ \"09:30-17:00\" ] ] }, { \"dayOfWeek\":4, \"timeList\":[ [ \"09:30-17:00\" ] ] }, { \"dayOfWeek\":5, \"timeList\":[ [ \"09:30-17:00\" ] ] }, { \"dayOfWeek\":6, \"timeList\":[ [ \"09:30-17:00\" ] ] }, { \"dayOfWeek\":7, \"timeList\":[ [ \"09:30-17:00\" ] ] } ] }"
        }
    }
    def beforeInsert(){
        if(this.class!=Channel && (this.openTime==null || this.openTime == "")){
            this.openTime = "{ \"standard\":[ { \"dayOfWeek\":1, \"timeList\":[ [ \"09:30-17:00\" ] ] }, { \"dayOfWeek\":2, \"timeList\":[ [ \"09:30-17:00\" ] ] }, { \"dayOfWeek\":3, \"timeList\":[ [ \"09:30-17:00\" ] ] }, { \"dayOfWeek\":4, \"timeList\":[ [ \"09:30-17:00\" ] ] }, { \"dayOfWeek\":5, \"timeList\":[ [ \"09:30-17:00\" ] ] }, { \"dayOfWeek\":6, \"timeList\":[ [ \"09:30-17:00\" ] ] }, { \"dayOfWeek\":7, \"timeList\":[ [ \"09:30-17:00\" ] ] } ] }"
        }
    }
}
