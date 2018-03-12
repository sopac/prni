package prni

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BasicController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Basic.list(params), model:[basicCount: Basic.count()]
    }

    def show(Basic basic) {
        respond basic
    }

    def create() {
        respond new Basic(params)
    }

    @Transactional
    def save(Basic basic) {
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

    def edit(Basic basic) {
        respond basic
    }

    @Transactional
    def update(Basic basic) {
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
    def delete(Basic basic) {

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
