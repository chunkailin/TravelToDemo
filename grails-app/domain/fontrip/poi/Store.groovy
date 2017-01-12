package fontrip.poi

class Store  extends Poi{

    final static String SPECIAL_STORE_ACCOUNT_CODE_THSR = "ST0000THSR"
    final static String SPECIAL_STORE_ACCOUNT_CODE_17LIFE = "ST0017LIFE"
    final static String SPECIAL_STORE_ACCOUNT_CODE_LION_ETICKET = "STLIONETKT"
    static hasMany		= []

    //預約模組設定
    boolean moduleBooking = false

    //可以自訂分店
    boolean moduleBranch = false

    static transients = []

    Set<StoreBranch> getStoreBranchList(){
        StoreBranch.findAllByStore(this)
    }

    def checkAccountCode(String accountCode){
        def tmpAccountCode = accountCode
        while(Store.findByAccountCode(tmpAccountCode) ){
            tmpAccountCode = 'ST'+stringService.generateRandomWord(8,true)
        }
        return  tmpAccountCode
    }
    def beforeInsert() {
        def tmpAccountCode = 'ST'+stringService.generateRandomWord(8,true)
        while(Store.findByAccountCode(tmpAccountCode) ){
            tmpAccountCode = 'ST'+stringService.generateRandomWord(8,true)
        }
        this.accountCode = tmpAccountCode
        searchable = true
    }

    def afterInsert(){

//		addToRoles(
//			new StoreRole(
//				authority:"ROLE_STORE_ADMIN_"+this.id,
//				store:this
//			)
//		)

    }
}
