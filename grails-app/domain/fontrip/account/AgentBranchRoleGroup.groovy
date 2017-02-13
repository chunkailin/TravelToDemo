package fontrip.account

import fontrip.poi.Agent
import fontrip.poi.AgentBranch

/**
 * StoreRoleGroup
 * A domain class describes the data object and it's mapping to the database
 */
class AgentBranchRoleGroup extends RoleGroup{
    AgentBranch agentBranch


    static mapping = {
    }

    static constraints = {
        agentBranch(nullable:false)
    }

    AgentBranchRoleGroup(String name, AgentBranch agentBranch, boolean defaultRoleGroup=false) {
        super(name, defaultRoleGroup)
        this.agentBranch = agentBranch
    }

    @Override
    public String toString() {
        return "AgentBranchRoleGroup{" +
                "agentBranch=" + agentBranch +
                '}';
    }
}
