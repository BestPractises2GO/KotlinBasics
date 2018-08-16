package bp2go.kotlinbasics.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.utils.inflate
import kotlinx.android.synthetic.main.list_user.view.*
import javax.inject.Inject


class ListUserAdapter constructor(val listener: ((String) -> Unit)) : RecyclerView.Adapter<ListUserAdapter.SimpleViewHolder>(){
    companion object {
        var userList: ArrayList<String> = ArrayList()
    }


    fun addUsers(users: ArrayList<String>){
        userList = users
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val v = parent.inflate(R.layout.list_user)
        return SimpleViewHolder(v)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) : Unit = holder.bindData(position, listener)


    override fun getItemCount(): Int = userList.size


    class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int, listener: (String) -> Unit) = with(itemView){
            text_user1.text = userList.get(position)
            setOnClickListener { listener(userList.get(position)) }
        }
    }
}