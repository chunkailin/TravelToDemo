package fontrip.poi

import fontrip.account.RoleGroupRole
import fontrip.account.User
import fontrip.account.UserRoleGroup
import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * PoiPoiUserMapping
 * A domain class describes the data object and it's mapping to the database
 */
class PoiUserMapping implements Serializable {

    Poi poi
    User user
    static belongsTo	= [poi:Poi,user:User]
    boolean equals(other) {
        if (!(other instanceof PoiUserMapping)) {
            return false
        }

        other.poi?.id == poi?.id &&
                other.user?.id == user?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (poi) builder.append(poi.id)
        if (user) builder.append(user.id)
        builder.toHashCode()
    }

    static PoiUserMapping get(long poiId, long userId) {
        find 'from PoiUserMapping where poi.id=:poiId and user.id=:userId',
                [poiId: poiId, userId: userId]
    }

    static PoiUserMapping create(Poi poi, User user, boolean flush = false) {
        def instance = null
        if(!PoiUserMapping.get(poi.id,user.id)) {
            instance = new PoiUserMapping(poi: poi, user: user).save(flush: flush, insert: true)
        }
        if(!user.backendUser){
            user.backendUser = true
            user.save(flush: flush)
        }
        return instance
    }



    void afterDelete(){

    }

    static boolean remove(Poi poi, User user, boolean flush = false) {
        PoiUserMapping instance = PoiUserMapping.findByPoiAndUser(poi, user)
        if (!instance) {
            return false
        }
        instance.delete(flush: flush)
        //檢查backendUser欄位
        def roleGroupCount = UserRoleGroup.countByUser(user)
        def poiMappingCount = PoiUserMapping.countByUser(user)
        if(roleGroupCount<2 && user.backendUser && poiMappingCount<1){
            user.backendUser = false
            user.save(flush: true)
        }
        true
    }

    static void removeAll(Poi poi) {
        def userList = PoiUserMapping.findAllByPoi(poi).user
        executeUpdate 'DELETE FROM PoiUserMapping WHERE poi=:poi', [poi: poi]
        for(user in userList){
            //檢查backendUser欄位
            def roleGroupCount = UserRoleGroup.countByUser(user)
            def poiMappingCount = PoiUserMapping.countByUser(user)
            if(roleGroupCount<2 && user.backendUser && poiMappingCount<1){
                user.backendUser = false
                user.save(flush: true)
            }
        }
    }
    static void removeAll(User user) {
        executeUpdate 'DELETE FROM PoiUserMapping WHERE user=:user', [user: user]

        //檢查backendUser欄位
        def roleGroupCount = UserRoleGroup.countByUser(user)
        if(roleGroupCount<2 && user.backendUser){
            user.backendUser = false
            user.save(flush: true)
        }

    }

    static boolean exists(Poi poiInstance, User userInstance) {
        return (PoiUserMapping.countByPoiAndUser(poiInstance,userInstance) > 0)
    }


    static mapping = {
        id composite: ['poi', 'user']
        version false
    }
}
