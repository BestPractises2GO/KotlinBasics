package bp2go.kotlinbasics

class Greeter(private val user:User3) {
    fun getEnglishGreeting() = "hello ${user.fullname()}"


}

class User3(
         val firstName:String,
         val lastName: String
){

    fun fullname() : String = "$firstName $lastName"

}