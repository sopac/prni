package prni

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MedinDetailedController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MedinDetailed.list(params), model:[medinDetailedCount: MedinDetailed.count()]
    }

    def show(MedinDetailed medinDetailed) {
        respond medinDetailed
    }

    def create() {
        respond new MedinDetailed(params)
    }

    @Transactional
    def save(MedinDetailed medinDetailed) {
        if (medinDetailed == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (medinDetailed.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond medinDetailed.errors, view:'create'
            return
        }

        medinDetailed.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'medinDetailed.label', default: 'MedinDetailed'), medinDetailed.id])
                redirect medinDetailed
            }
            '*' { respond medinDetailed, [status: CREATED] }
        }
    }

    def edit(MedinDetailed medinDetailed) {
        respond medinDetailed
    }

    @Transactional
    def update(MedinDetailed medinDetailed) {
        if (medinDetailed == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (medinDetailed.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond medinDetailed.errors, view:'edit'
            return
        }

        medinDetailed.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'medinDetailed.label', default: 'MedinDetailed'), medinDetailed.id])
                redirect medinDetailed
            }
            '*'{ respond medinDetailed, [status: OK] }
        }
    }

    @Transactional
    def delete(MedinDetailed medinDetailed) {

        if (medinDetailed == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        medinDetailed.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'medinDetailed.label', default: 'MedinDetailed'), medinDetailed.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'medinDetailed.label', default: 'MedinDetailed'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
