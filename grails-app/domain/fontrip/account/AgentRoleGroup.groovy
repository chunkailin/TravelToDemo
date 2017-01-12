package fontrip.account

import fontrip.poi.Agent

/**
 * StoreRoleGroup
 * A domain class describes the data object and it's mapping to the database
 */
class AgentRoleGroup extends RoleGroup{
    Agent agent


    static mapping = {
    }

    static constraints = {
        agent(nullable:false)
    }

    AgentRoleGroup(String name,  Agent agent, boolean defaultRoleGroup=false) {
        super(name, defaultRoleGroup)
        this.agent = agent
    }

    @Override
    public String toString() {
        return "AgentRoleGroup{" +
                "agent=" + agent +
                '}';
    }
}
