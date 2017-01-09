package fontrip.account

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class UserRoleGroup implements Serializable {

	private static final long serialVersionUID = 1

    User user
    RoleGroup roleGroup

    UserRoleGroup(User u, RoleGroup rg) {
        user = u
        roleGroup = rg
    }

	@Override
	boolean equals(other) {
		if (!(other instanceof UserRoleGroup)) {
			return false
		}

		other.user?.id == user?.id && other.roleGroup?.id == roleGroup?.id
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (roleGroup) builder.append(roleGroup.id)
		builder.toHashCode()
	}

	static UserRoleGroup get(long userId, long roleGroupId) {
		criteriaFor(userId, roleGroupId).get()
	}

	static boolean exists(long userId, long roleGroupId) {
		criteriaFor(userId, roleGroupId).count()
	}

	private static DetachedCriteria criteriaFor(long userId, long roleGroupId) {
		UserRoleGroup.where {
			user == User.load(userId) &&
			roleGroup == RoleGroup.load(roleGroupId)
		}
	}

	static UserRoleGroup create(User user, RoleGroup roleGroup, boolean flush = false) {
		def instance = new UserRoleGroup(user: user, roleGroup: roleGroup)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(User u, RoleGroup r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = UserRoleGroup.where { user == u && roleGroup == r }.deleteAll()

		if (flush) { UserRoleGroup.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(User u, boolean flush = false) {
		if (u == null) return

		UserRoleGroup.where { user == u }.deleteAll()

		if (flush) { UserRoleGroup.withSession { it.flush() } }
	}

	static void removeAll(RoleGroup r, boolean flush = false) {
		if (r == null) return

		UserRoleGroup.where { roleGroup == r }.deleteAll()

		if (flush) { UserRoleGroup.withSession { it.flush() } }
	}

	static constraints = {
		roleGroup validator: { RoleGroup r, UserRoleGroup ur ->
			if (ur.user == null || ur.user.id == null) return
			boolean existing = false
			UserRoleGroup.withNewSession {
				existing = UserRoleGroup.exists(ur.user.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['user', 'roleGroup']
		version false
	}
}
