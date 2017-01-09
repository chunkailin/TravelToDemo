package fontrip.account

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='name')
@ToString(includes='name', includeNames=true, includePackage=false)
class RoleGroup implements Serializable {

    //for i18N查詢使用
    final static String NAMECODE_PREFIX = "role.name."
    //for 使用者自定義roleGroup時，自動建立name使用
    final static String NAME_PREFIX = "roleGroup.name.Customize"

    transient stringService
    static transients = ['stringService']

    private static final long serialVersionUID = 1

    Long id
    Long version

    String name = NAME_PREFIX

    boolean defaultRoleGroup = false

    Date dateCreated
    Date lastUpdated

    RoleGroup(String name,boolean defaultRoleGroup=false) {
        this.name = this.name
        this.defaultRoleGroup = defaultRoleGroup
    }

    Set<Role> getAuthorities() {
        RoleGroupRole.findAllByRoleGroup(this)*.role
    }

    String getNameCode(){
        if(this.defaultRoleGroup)
            return NAMECODE_PREFIX + this.name
        else
            return NAMECODE_PREFIX + this.id
    }

	static constraints = {
		name blank: false, nullable: false
	}

	static mapping = {
		cache true
	}
}
