package fontrip.location

import org.apache.commons.lang.builder.HashCodeBuilder

import fontrip.poi.Poi
class LocationAreaMapping implements Serializable {

    Location location
    Area area
    Poi poi
    static belongsTo	= [location:Location,area:Area]
    boolean equals(other) {
        if (!(other instanceof LocationAreaMapping)) {
            return false
        }

        other.location?.id == location?.id &&
                other.area?.id == area?.id
    }
    static constraints = {
        poi(nullable:true)
    }
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (location) builder.append(location.id)
        if (area) builder.append(area.id)
        builder.toHashCode()
    }

    static LocationAreaMapping get(long locationId, long areaId) {
        find 'from LocationAreaMapping where location.id=:locationId and area.id=:areaId',
                [locationId: locationId, areaId: areaId]
    }

    static LocationAreaMapping create(Location location, Area area, boolean flush = false) {
        new LocationAreaMapping(location: location, area: area).save(flush: flush, insert: true)
    }
    static LocationAreaMapping create(Location location, Area area, Poi poi,boolean flush = false) {
        new LocationAreaMapping(location: location, area: area,poi:poi).save(flush: flush, insert: true)
    }

    static boolean remove(Location location, Area area, boolean flush = false) {
        LocationAreaMapping instance = LocationAreaMapping.findByLocationAndArea(location, area)
        if (!instance) {
            return false
        }

        instance.delete(flush: flush)
        true
    }

    static void removeAll(Location location) {
        executeUpdate 'DELETE FROM LocationAreaMapping WHERE location=:location', [location: location]
    }
    static void removeAll(Area area) {
        executeUpdate 'DELETE FROM LocationAreaMapping WHERE area=:area', [area: area]
    }

    static boolean exists(long locationId, long areaId) {
        LocationAreaMapping.where {
            location == Location.load(locationId) &&
                    area == Area.load(areaId)
        }.count() > 0
    }

    static mapping = {
        id composite: ['location', 'area']
        version false
    }
}
