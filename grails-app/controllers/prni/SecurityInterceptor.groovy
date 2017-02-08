package prni


class SecurityInterceptor {

    SecurityInterceptor() {
        matchAll().except(controller:'login').except(controller:'help').except(uri:"/").except(uri:"/prni/").except(uri:"prni").except(uri:"prni/").except(uri:"/prni")
    }

    boolean before() {
        if (session['aid'] == null){
            redirect(controller: 'login')
            return false
        }
        return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
