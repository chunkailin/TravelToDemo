package fontrip.account

class AdminRoleGroup extends RoleGroup {

    static mapping = {
        //id generator: 'uuid'
    }

    static constraints = {

        //attribute(unique:true)
    }

    AdminRoleGroup(String name, boolean defaultRoleGroup=false) {
        super(name, defaultRoleGroup)
    }
}
