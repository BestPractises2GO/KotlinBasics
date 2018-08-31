package bp2go.kotlinbasics

interface BookService {
    fun inStock(bookId: Int): Boolean
    fun lend(bookId: Int, memberId: Int)
}


class LendBookManager(val bookService:BookService) {
    fun checkout(bookId: Int, memberId: Int) {
        if(bookService.inStock(bookId)) {
            bookService.lend(bookId, memberId)
        } else {
            throw IllegalStateException("Book is not available")
        }
    }
}



class BookServiceImpl : BookService{
    override fun lend(bookId: Int, memberId: Int) {
        println("$bookId $memberId")
    }




    override fun inStock(bookId: Int): Boolean {
        if(bookId == 100) {
            return true
        }
        return false
    }

    fun normalMethod(){
        val p1 = LendBookManager(this)
        p1.checkout(30,50)
    }


}