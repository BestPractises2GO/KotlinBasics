package bp2go.kotlinbasics.view.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bp2go.kotlinbasics.R
import bp2go.kotlinbasics.injection.module.GlideApp
import bp2go.kotlinbasics.model.NetworkState
import bp2go.kotlinbasics.model.Status
import bp2go.kotlinbasics.model.User2
import bp2go.kotlinbasics.utils.inflate
import kotlinx.android.synthetic.main.item_network_state.view.*
import kotlinx.android.synthetic.main.item_user.view.*
import kotlinx.android.synthetic.main.list_user.view.*
import javax.inject.Inject


class ListUserAdapter constructor(private val retryCallback: () -> Unit) : PagedListAdapter<User2, RecyclerView.ViewHolder>(UserDiffCallback){

    private var networkState: NetworkState? = null


    companion object {
        var userList: ArrayList<String> = ArrayList()
        //Berechne Differenz zwischen alter und neuer Liste und aktualisiere die alte Liste in die Neue
        //output is a list of update operations that converts the first list into the second one.
        val UserDiffCallback = object : DiffUtil.ItemCallback<User2>(){
            override fun areItemsTheSame(oldItem: User2?, newItem: User2?): Boolean {
                return oldItem?.id  == newItem?.id
            }
            override fun areContentsTheSame(oldItem: User2?, newItem: User2?): Boolean {
                return oldItem == newItem
            }
        }
    }

    @Deprecated("notUsed")
    fun addUsers(users: ArrayList<String>){
        userList = users
    }

    //(2)Gib den Viewholder entsprechend des GetItemViewType für ein Element zurück
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      /*  val v = parent.inflate(R.layout.list_user)
        return SimpleViewHolder(v) */
        return when(viewType){
            R.layout.item_user -> UserViewHolder.create(parent)
            R.layout.item_network_state -> NetworkStateViewHolder.create(parent, retryCallback)
            else -> throw IllegalArgumentException("kenne den ViewType nicht")
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    //(1)Gib das Layout für das jeweilige Element zurück
    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_user
        }
    }

    //(3)Wähle die ViewHolder Methoden auf, jenachdem welches Layout getItemViewType für die jeweilige Position entspricht
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)  : Unit{
        Log.e("posi", holder.toString()+" "+position)
        when(getItemViewType(position)){
            R.layout.item_user ->   (holder as UserViewHolder).bindData(getItem(position))
            R.layout.item_network_state -> (holder as NetworkStateViewHolder).bindTo(networkState)
        }
    }

    override fun getItemCount(): Int = super.getItemCount() + if(hasExtraRow()) 1 else 0


    class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int, listener: (String) -> Unit) = with(itemView){
            text_user1.text = userList.get(position)
            setOnClickListener { listener(userList.get(position)) }
        }
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindData(user: User2?)= with(itemView){
            UserName.text = user?.login
            GlideApp.with(context)
                    .load(user?.avatarUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(UserAvatar)
            siteAdminIcon.visibility = if(user!!.siteAdmin) View.VISIBLE else View.GONE
        }
        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_user, parent, false)
                return UserViewHolder(view)
            }
        }
    }



    class NetworkStateViewHolder(val view: View, private val retryCallback: () -> Unit) : RecyclerView.ViewHolder(view) {

        init {
            itemView.retryLoadingButton.setOnClickListener { retryCallback() }
        }

        fun bindTo(networkState: NetworkState?) {
            //error message
            itemView.errorMessageTextView.visibility = if (networkState?.message != null) View.VISIBLE else View.GONE
            if (networkState?.message != null) {
                itemView.errorMessageTextView.text = networkState.message
            }

            //loading and retry
            itemView.retryLoadingButton.visibility = if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
            itemView.loadingProgressBar.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE
        }

        companion object {
            fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_network_state, parent, false)
                return NetworkStateViewHolder(view, retryCallback)
            }
        }

    }


    /**
     * Set the current network state to the adapter
     * but this work only after the initial load
     * and the adapter already have list to add new loading raw to it
     * so the initial loading state the activity responsible for handle it
     *
     * @param newNetworkState the new network state
     */
    fun setNetworkState(newNetworkState: NetworkState?) {
        if (currentList != null) {
            if (currentList!!.size != 0) {
                val previousState = this.networkState
                val hadExtraRow = hasExtraRow()
                this.networkState = newNetworkState
                val hasExtraRow = hasExtraRow()
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) {
                        notifyItemRemoved(super.getItemCount())
                    } else {
                        notifyItemInserted(super.getItemCount())
                    }
                } else if (hasExtraRow && previousState !== newNetworkState) {
                    notifyItemChanged(itemCount - 1)
                }
            }
        }
    }




}