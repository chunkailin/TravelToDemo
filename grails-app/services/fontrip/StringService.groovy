package fontrip

import grails.transaction.Transactional

import org.hashids.Hashids

@Transactional
class StringService {
    def grailsApplication

    static String encodeHashid(clazz,id){
        def hashidObj = new Hashids(clazz.name.replace('.',"_"))
        return hashidObj.encode(id)
    }
    static long decodeHashid(clazz,hashid){
        def hashidObj = new Hashids(clazz.name.replace('.',"_"))
        return hashidObj.decode(hashid)[0]
    }
}
