package fontrip.poi

//import fontrip.product.ProductContract

class Agent extends Poi{
    String agentType = AGENT_TYPE_TRAVEL
    final static String AGENT_TYPE_TRAVEL = "TRAVEL"
    final static String AGENT_TYPE_RETAIL = "RETAIL"
    public static transient agentTypeList = [
            AGENT_TYPE_TRAVEL,
            AGENT_TYPE_RETAIL
    ]

    //多階層銷售
    boolean moduleMultiLevel = false
    //紅利積點模組
    boolean moduleBonus = false

    //整合雄獅系統
    boolean moduleLionIntegration = false

    //整合17Life系統
    boolean module17LifeIntegration = false

    //整合雄獅電子票券系統
    boolean moduleLionElectronicIntegration = false

    //20160926 高鐵模組
    boolean moduleThsr = false

    //信託模組
    boolean moduleTrust = false

    //可以自訂分店
    boolean moduleBranch = false

    static hasMany		= []

//    boolean isDeletable(){
//        if(ProductContract.countByBuyer(this)>0) return false
//
//        return true
//    }

    def beforeInsert() {
        def tmpAccountCode = 'AG'+stringService.generateRandomWord(8,true)
        while(Agent.findByAccountCode(tmpAccountCode) ){
            tmpAccountCode = 'AG'+stringService.generateRandomWord(8,true)
        }
        accountCode = tmpAccountCode
    }
    def beforeDelete() {
        //TODO 刪除代理商要做的事

        //刪除UserRoleGroup

        //刪除AgentRoleGroup

        //刪除AgentConfig


    }
}
