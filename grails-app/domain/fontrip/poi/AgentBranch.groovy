package fontrip.poi

/**
 * StoreBranch
 * A domain class describes the data object and it's mapping to the database
 */
class AgentBranch extends Poi{
    Agent agent
    static hasMany		= []

    def beforeInsert() {
        def tmpAccountCode = 'AB'+stringService.generateRandomWord(8,true)
        while(AgentBranch.findByAccountCode(tmpAccountCode) ){
            tmpAccountCode = 'AB'+stringService.generateRandomWord(8,true)
        }
        accountCode = tmpAccountCode
    }
}
