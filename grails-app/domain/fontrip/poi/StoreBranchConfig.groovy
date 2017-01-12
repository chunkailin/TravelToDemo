package fontrip.poi

import fontrip.SysConfig

/**
 * StoreBranchConfig
 * A domain class describes the data object and it's mapping to the database
 */
class StoreBranchConfig extends SysConfig{

    static	belongsTo	= [storeBranch:StoreBranch]	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
    static	mapping = {
        //id generator: 'uuid'
    }

    static	constraints = {
        storeBranch(nullable:false)
        //attribute(unique:true)
    }

}
