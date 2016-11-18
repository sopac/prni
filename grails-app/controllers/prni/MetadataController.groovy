package prni

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MetadataController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    String _countryCode
    String _type

    def index(Integer max, String countryCode, String type) {

        if (countryCode != null && countryCode.equals("all")){
            _type = null
            type = null
            _countryCode = null
            countryCode = null
        }

        if (countryCode != null) _type = null
        if (type != null) _countryCode = null



        if (countryCode != null) _countryCode = countryCode
        if (countryCode == null) countryCode = _countryCode

        if (type != null) _type = type
        if (type == null) type = _type


        if (type != null) {
            countryCode = null
            def list = Metadata.findAllByResourceType(type, params)
            respond list, model: [metadataCount: list.size(), header: 'Listing ' + type + 's']
        }

        if (countryCode != null) {
            type == null
            Country c = Country.findByCode(countryCode)
            def list = Metadata.findAllByCountry(c, params)
            respond list, model: [metadataCount: list.size(), header: 'Listing ' + c.getName() + " Metadata"]
        }

        if (countryCode == null && type == null) {
            params.max = Math.min(max ?: 20, 100)
            respond Metadata.list(params), model: [metadataCount: Metadata.count(), header: 'Listing Complete Metadata']
        }
    }

    def show(Metadata metadata) {
        respond metadata
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
