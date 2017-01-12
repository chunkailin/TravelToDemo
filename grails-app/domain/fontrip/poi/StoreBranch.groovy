package fontrip.poi



/**
 * StoreBranch
 * A domain class describes the data object and it's mapping to the database
 */
class StoreBranch extends Poi{
    Store store
    static hasMany		= []

//    boolean isDeletable(){
//        def poiDeviceMapping = PoiDeviceMapping.findByBranch(this)
//        if(!poiDeviceMapping) return true
//        if(ProductOrder.countByRedeemDevice(poiDeviceMapping.poiDevice) >0) return false
//        return true
//    }

    def beforeInsert() {
        def tmpAccountCode = 'SB'+stringService.generateRandomWord(8,true)
        while(StoreBranch.findByAccountCode(tmpAccountCode) ){
            tmpAccountCode = 'SB'+stringService.generateRandomWord(8,true)
        }
        accountCode = tmpAccountCode
    }
}
