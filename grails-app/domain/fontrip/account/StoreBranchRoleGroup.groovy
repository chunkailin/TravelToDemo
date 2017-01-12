package fontrip.account

import fontrip.poi.StoreBranch

/**
 * StoreRoleGroup
 * A domain class describes the data object and it's mapping to the database
 */
class StoreBranchRoleGroup extends RoleGroup{
    StoreBranch storeBranch


    static mapping = {
    }

    static constraints = {
        storeBranch(nullable:false)
    }

    StoreBranchRoleGroup(String name, StoreBranch storeBranch, boolean defaultRoleGroup=false) {
        super(name, defaultRoleGroup)
        this.storeBranch = storeBranch
    }

    @Override
    public String toString() {
        return "StoreBranchRoleGroup{" +
                "storeBranch=" + storeBranch +
                '}';
    }
}
