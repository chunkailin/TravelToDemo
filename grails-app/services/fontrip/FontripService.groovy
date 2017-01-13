package fontrip

import grails.transaction.Transactional
import grails.util.Environment

@Transactional
class FontripService {
    def grailsApplication
    static def mainPhotoName = (Environment.current.name in ['fontrip_demo','development_lion','production_lion'])?"FontripDefaultPhoto_Lion.jpg":"FontripDefaultPhoto.jpg"
}
