package fontrip.account

import fontrip.poi.Channel

/**
 * ChannelRoleGroup
 * A domain class describes the data object and it's mapping to the database
 */
class ChannelRoleGroup extends RoleGroup{
    Channel channel


    static mapping = {
    }

    static constraints = {
        channel(nullable:false)
    }

    ChannelRoleGroup(String name, Channel channel, boolean defaultRoleGroup=false) {
        super(name, defaultRoleGroup)
        this.channel = channel
    }

    @Override
    public String toString() {
        return "ChannelRoleGroup{" +
                "channel=" + channel +
                '}';
    }
}
