package fontrip.poi

import fontrip.SysConfig

/**
 * ChannelConfig
 * A domain class describes the data object and it's mapping to the database
 */
class ChannelConfig extends SysConfig{
    boolean editByChannel = false
    static	belongsTo	= [channel:Channel]	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
    static	mapping = {
        //id generator: 'uuid'

    }

    static	constraints = {
        channel(nullable:false)
        //attribute(unique:true)
    }
}
