package prni

class SearchController {

    def index(String query) {
        if (query == null) query = "fiji"
        def res = MetadataObselete.search(query)
        respond res.searchResults, model: [total: res.total, header: query]
    }
}
