package fontrip.poi

import fontrip.SysFile
import fontrip.SysLink
import fontrip.account.User

class PoiDetail {

    transient stringService
    /* Default (injected) attributes of GORM */
    Long	id
    Long	version

    /* Automatic timestamping of GORM */
    Date	dateCreated
    Date	lastUpdated

    String name
    String description
    String brief
    String address
    String trafficInformation


    List links = new ArrayList();
    List files = new ArrayList();
    List photos = new ArrayList();

    String lang;
    User contactUser;
    String contactUserName
    String openTimeInformation //營業時間備註



    static	belongsTo	= [poi:Poi]	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
//	static	hasOne		= []	// tells GORM to associate another domain object as an owner in a 1-1 mapping
    static 	hasMany		= [links:SysLink,photos:SysFile,files:SysFile]
//	static	mappedBy	= []	// specifies which property should be used in a mapping

    static	mapping = {
        description type: 'text'
        brief type: 'text'
        trafficInformation type: 'text'
        openTimeInformation type: 'text'
    }

    static	constraints = {
        contactUser(nullable:true)
        contactUserName(nullable:true)
        name(nullable:false)
        description(nullable:true)
        brief(nullable:true)
        address(nullable:true)
        trafficInformation(nullable:true)
        openTimeInformation(nullable:true)
    }

    public String getHashid(){
        if(this.class==StoreDetail)
            return stringService.encodeHashid(StoreDetail,id)
        else if(this.class==StoreBranchDetail)
            return stringService.encodeHashid(StoreBranchDetail,id)
        else if(this.class==AgentDetail)
            return stringService.encodeHashid(AgentDetail,id)
        else if(this.class==AgentBranchDetail)
            return stringService.encodeHashid(AgentBranchDetail,id)
        else if(this.class==SpotDetail)
            return stringService.encodeHashid(SpotDetail,id)
        else if(this.class==ChannelDetail)
            return stringService.encodeHashid(ChannelDetail,id)
        else
            return null
    }
}
