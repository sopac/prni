package prni

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BasicController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Metadata.list(params), model:[basicCount: Metadata.count()]
    }

    def show(Metadata basic) {
        respond basic
    }

    def create() {
        respond new Metadata(params)
    }

    @Transactional
    def save(Metadata basic) {
        if (basic == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (basic.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond basic.errors, view:'create'
            return
        }

        basic.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'basic.label', default: 'Basic'), basic.id])
                redirect basic
            }
            '*' { respond basic, [status: CREATED] }
        }
    }

    def edit(Metadata basic) {
        respond basic
    }

    @Transactional
    def update(Metadata basic) {
        if (basic == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (basic.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond basic.errors, view:'edit'
            return
        }

        basic.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'basic.label', default: 'Basic'), basic.id])
                redirect basic
            }
            '*'{ respond basic, [status: OK] }
        }
    }

    @Transactional
    def delete(Metadata basic) {

        if (basic == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        basic.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'basic.label', default: 'Basic'), basic.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'basic.label', default: 'Basic'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
