package fontrip.poi

import fontrip.SysConfig

/**
 * StoreConfig
 * A domain class describes the data object and it's mapping to the database
 */
class AgentConfig extends SysConfig{
    boolean editByAgent = false
    static	belongsTo	= [agent:Agent]	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
    static	mapping = {
        //id generator: 'uuid'

    }

    static	constraints = {
        agent(nullable:false)
        //attribute(unique:true)
    }

}
