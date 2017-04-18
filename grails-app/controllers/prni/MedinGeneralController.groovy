package prni

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MedinGeneralController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MedinGeneral.list(params), model:[medinGeneralCount: MedinGeneral.count()]
    }

    def show(MedinGeneral medinGeneral) {
        respond medinGeneral
    }

    def create() {
        respond new MedinGeneral(params)
    }

    @Transactional
    def save(MedinGeneral medinGeneral) {
        if (medinGeneral == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (medinGeneral.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond medinGeneral.errors, view:'create'
            return
        }

        medinGeneral.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'medinGeneral.label', default: 'MedinGeneral'), medinGeneral.id])
                redirect medinGeneral
            }
            '*' { respond medinGeneral, [status: CREATED] }
        }
    }

    def edit(MedinGeneral medinGeneral) {
        respond medinGeneral
    }

    @Transactional
    def update(MedinGeneral medinGeneral) {
        if (medinGeneral == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (medinGeneral.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond medinGeneral.errors, view:'edit'
            return
        }

        medinGeneral.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'medinGeneral.label', default: 'MedinGeneral'), medinGeneral.id])
                redirect medinGeneral
            }
            '*'{ respond medinGeneral, [status: OK] }
        }
    }

    @Transactional
    def delete(MedinGeneral medinGeneral) {

        if (medinGeneral == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        medinGeneral.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'medinGeneral.label', default: 'MedinGeneral'), medinGeneral.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'medinGeneral.label', default: 'MedinGeneral'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
