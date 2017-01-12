package fontrip.account

import fontrip.poi.Store

/**
 * StoreRoleGroup
 * A domain class describes the data object and it's mapping to the database
 */
class StoreRoleGroup extends RoleGroup{
    Store store


    static mapping = {
    }

    static constraints = {
        store(nullable:false)
    }

    StoreRoleGroup(String name,  Store store, boolean defaultRoleGroup=false) {
        super(name, defaultRoleGroup)
        this.store = store
    }

    @Override
    public String toString() {
        return "StoreRoleGroup{" +
                "store=" + store +
                '}';
    }
}
