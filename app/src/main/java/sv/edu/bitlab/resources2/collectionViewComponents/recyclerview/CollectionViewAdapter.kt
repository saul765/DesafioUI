package sv.edu.bitlab.tarea6.ordenHistorial.recyclerView

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import sv.edu.bitlab.resources2.R
import sv.edu.bitlab.resources2.models.Account


class CollectionViewAdapter(var accounts:ArrayList<Account>, val listener: CollectionViewHolder.ReservationItemListener, var context:Context
) : RecyclerView.Adapter<CollectionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_collection_list_view, parent, false)
        return CollectionViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.bindData()

        holder.name_txt?.text = accounts[position].accountName
        holder.email_txt?.text = accounts[position].accountEmail
        holder.telephopne_txt?.text= accounts[position].accountPhone

        val foundBy=context.resources.getString(R.string.collection_view_found_by)
        holder.found_by_txt?.text = context.resources.getString(
            R.string.two_format_string,
            foundBy,
            accounts[position].accountFoundOutBy
        )
        Glide.with(context).load(accounts[position].accountImage).into(holder.imageView!!);


    }


    override fun getItemCount(): Int {

        return accounts.size

    }
}



