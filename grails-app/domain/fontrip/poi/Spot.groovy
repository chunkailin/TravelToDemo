package fontrip.poi

class Spot extends Poi{

    static hasMany		= []

    def beforeInsert() {
        def tmpAccountCode = 'SP'+stringService.generateRandomWord(8,true)
        while(Spot.findByAccountCode(tmpAccountCode) ){
            tmpAccountCode = 'SP'+stringService.generateRandomWord(8,true)
        }
        accountCode = tmpAccountCode
        searchable = true
    }

}
