package prni

import grails.test.mixin.*
import spock.lang.*

@TestFor(MetadataController)
@Mock(Metadata)
class MetadataControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.metadataList
            model.metadataCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.metadata!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def metadata = new Metadata()
            metadata.validate()
            controller.save(metadata)

        then:"The create view is rendered again with the correct model"
            model.metadata!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            metadata = new Metadata(params)

            controller.save(metadata)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/metadata/show/1'
            controller.flash.message != null
            Metadata.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def metadata = new Metadata(params)
            controller.show(metadata)

        then:"A model is populated containing the domain instance"
            model.metadata == metadata
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def metadata = new Metadata(params)
            controller.edit(metadata)

        then:"A model is populated containing the domain instance"
            model.metadata == metadata
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/metadata/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def metadata = new Metadata()
            metadata.validate()
            controller.update(metadata)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.metadata == metadata

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            metadata = new Metadata(params).save(flush: true)
            controller.update(metadata)

        then:"A redirect is issued to the show action"
            metadata != null
            response.redirectedUrl == "/metadata/show/$metadata.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/metadata/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def metadata = new Metadata(params).save(flush: true)

        then:"It exists"
            Metadata.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(metadata)

        then:"The instance is deleted"
            Metadata.count() == 0
            response.redirectedUrl == '/metadata/index'
            flash.message != null
    }
}
