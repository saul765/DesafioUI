package sv.edu.bitlab.tarea6.ordenHistorial.recyclerView

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_collection_list_view.view.*


class CollectionViewHolder(itemView: View, val listener: ReservationItemListener) : RecyclerView.ViewHolder(itemView)  {

    var name_txt: TextView? = null
    var email_txt: TextView? =null
    var telephopne_txt: TextView?=null
    var found_by_txt:TextView? =null
    var round_txt:TextView?=null
    var schedule_txt:TextView?=null
    var imageView:CircleImageView?=null
    var container:View?=null



    fun bindData() {


        name_txt=itemView.txt_name
        email_txt=itemView.txt_user_email
        telephopne_txt=itemView.txt_user_telephone
        found_by_txt=itemView.txt_found_by
        imageView=itemView.img_view_user
        container=itemView.item_container_account




        container?.setOnClickListener{

            listener.onItemClickReservation(this.adapterPosition)

        }
        
    }


    interface ReservationItemListener{
       fun onItemClickReservation(position: Int)
        fun onItemClickDetalle(btn_detalle:Button,position: Int)
        fun onTextInput(input:String, position: Int)
    }
}