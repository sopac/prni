package prni

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MetadataController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def indexAll(Integer max) {
        params.max = Math.min(max ?: 900, 900)
        respond Metadata.list(params), model: [metadataCount: Metadata.count()]
    }

    def indexWithMedin(){
        def l = []
        Metadata.list().each { m->
            if (m.project != null){
                if (!m.project.equals("")){
                    l << m
                }
            }
        }
        respond l, model:[metadataCount: l.size()]
    }

    def indexFormat(String type){
        def l = []
        String type1 = "Datasets"
        Metadata.list().each { m ->
            if (type.equals("report")){
                type1 = "Reports"
                if (m.format.toLowerCase().startsWith("re")) l << m
            }
            if (type.equals("data")){
                if (m.format.toLowerCase().startsWith("da")) l << m
            }
        }
        respond l, model:[metadataCount: l.size(), type:type1]
    }

    def index(String countryCode) {
        def c = Country.findByCode(countryCode)
        def l = Metadata.findAllByCountry(c)
        respond l, model: [metadataCount: l.size(), country: c.getName()]
    }


    def show(Metadata metadata) {
        def medinGeneral = MedinGeneral.findByMetadata(metadata)
        def medinDetailed = MedinDetailed.findByMetadata(metadata)
        //Map data = [metadata:metadata, medinGeneral:medinGeneral, medinDetailed:medinDetailed]
        respond metadata, model: [medinGeneral: medinGeneral, medinDetailed: medinDetailed]
    }

    def create() {
        respond new Metadata(params)
    }

    @Transactional
    def save(Metadata metadata) {
        if (metadata == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (metadata.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond metadata.errors, view: 'create'
            return
        }

        metadata.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'metadata.label', default: 'Metadata'), metadata.id])
                redirect metadata
            }
            '*' { respond metadata, [status: CREATED] }
        }
    }

    def edit(Metadata metadata) {
        respond metadata
    }

    @Transactional
    def update(Metadata metadata) {
        if (metadata == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (metadata.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond metadata.errors, view: 'edit'
            return
        }

        metadata.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'metadata.label', default: 'Metadata'), metadata.id])
                redirect metadata
            }
            '*' { respond metadata, [status: OK] }
        }
    }

    @Transactional
    def delete(Metadata metadata) {

        if (metadata == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        metadata.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'metadata.label', default: 'Metadata'), metadata.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'metadata.label', default: 'Metadata'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
