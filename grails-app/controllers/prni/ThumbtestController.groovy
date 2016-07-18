package prni

class ThumbtestController {

    def index() {
        Metadata.list().each { m ->
            if (m.thumbnail != null){
                render "<a href='${createLink(controller: 'metadata', action: 'show', id:m.id)}'>" + m.name + "</a><br/>"
            }
        }
    }
}
