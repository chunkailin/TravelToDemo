package fontrip.poi

import fontrip.SysConfig

/**
 * AgentBranchConfig
 * A domain class describes the data object and it's mapping to the database
 */
class AgentBranchConfig extends SysConfig{

    static	belongsTo	= [agentBranch:AgentBranch]	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
    static	mapping = {
        //id generator: 'uuid'
    }

    static	constraints = {
        agentBranch(nullable:false)
        //attribute(unique:true)
    }

}
