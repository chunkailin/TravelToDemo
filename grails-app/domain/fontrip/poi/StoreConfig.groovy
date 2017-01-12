package fontrip.poi

import fontrip.SysConfig

/**
 * StoreConfig
 * A domain class describes the data object and it's mapping to the database
 */
class StoreConfig extends SysConfig{
    boolean editByStore = false

    static	belongsTo	= [store:Store]	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
    static	mapping = {
        //id generator: 'uuid'

    }

    static	constraints = {
        store(nullable:false)
        //attribute(unique:true)
    }


}
