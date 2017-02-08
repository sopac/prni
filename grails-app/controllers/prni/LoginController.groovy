package prni

class LoginController {

    def index(){}

    def auth(String email, String password) {
        //render email + "<br/>" + password

       Account a = Account.findByEmail(email)
        if (a.password.trim().equals(password.trim())){
            session['name'] = a.name
            session['aid'] = a.id
            flash.message = a.name + ", " + a.organisation + " logged in."
            redirect(uri: "/")
        } else {
            flash.message = "Login failed. Incorrect credentials provided."
            redirect(action: 'index')
        }
    }

    def logout(){
        session['name'] = null
        session['aid'] = null
        session.invalidate()
        redirect(uri: "/")
    }


}
