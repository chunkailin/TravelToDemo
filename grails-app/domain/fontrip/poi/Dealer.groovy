package fontrip.poi

class Dealer extends Poi {

    static hasMany		= []


    static transients = []


    def beforeInsert() {
        def tmpAccountCode = 'DL'+stringService.generateRandomWord(8,true)
        while(Dealer.findByAccountCode(tmpAccountCode) ){
            tmpAccountCode = 'DL'+stringService.generateRandomWord(8,true)
        }
        this.accountCode = tmpAccountCode
        searchable = true
    }




}
