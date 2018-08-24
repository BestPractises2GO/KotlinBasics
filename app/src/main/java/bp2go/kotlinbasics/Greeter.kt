package bp2go.kotlinbasics

class Greeter(private val user:User3, val bookService: BookService) {
    fun getEnglishGreeting() = "hello ${user.fullname()}"

    fun checkout(bookId: Int, memberId: Int){
        if(bookService.inStock(bookId)){
            bookService.lend(bookId, memberId)
        }else{
            throw IllegalStateException("Book not available")
        }
    }
}

class User3(
         val firstName:String,
         val lastName: String
){
    fun fullname() : String = "$firstName $lastName"

    val g1 = Greeter(User3("j","j"), BookServiceImpl())

    fun d(){
        g1.bookService.inStock(2)
    }

}