package sv.edu.bitlab.resources2.collectionViewComponents

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import sv.edu.bitlab.resources2.interfaces.OnFragmentInteractionListener
import sv.edu.bitlab.resources2.models.Account
import sv.edu.bitlab.tarea6.ordenHistorial.recyclerView.CollectionViewAdapter
import sv.edu.bitlab.tarea6.ordenHistorial.recyclerView.CollectionViewHolder
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_collection_view.view.*
import sv.edu.bitlab.resources2.FragmentsIndex
import sv.edu.bitlab.resources2.R





@Suppress("UNCHECKED_CAST")
class CollectionViewFragment : Fragment(),CollectionViewHolder.ReservationItemListener {



    private var listener:OnFragmentInteractionListener?=null
    private var listView: RecyclerView?=null
    private var accounts:ArrayList<Account>?=null
    var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        accounts= ArrayList()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val  view=inflater.inflate(R.layout.fragment_collection_view, container, false)

            view.txt_add_new_registry.setOnClickListener{

                listener?.onFragmentInteraction(FragmentsIndex.KEY_FRAGMENT_FORM)

            }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView=view.findViewById(R.id.recyclerview_collection_view)
        listView?.layoutManager = LinearLayoutManager(this.context!!)
        listView?.adapter = CollectionViewAdapter(accounts!!,this,requireContext())

        getAllAccounts()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onItemClickReservation(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemClickDetalle(btn_detalle: Button, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTextInput(input: String, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getAllAccounts(){


        db.collection("accounts")
            .get()
            .addOnSuccessListener { task ->

                    for (document in task) {
                        //Log.d("data", document.id + " => " + document.toObject(Account::class.java))
                        val account = document.toObject(Account::class.java)
                        accounts?.add(account)

                    }
                val adapter =listView?.adapter as CollectionViewAdapter
                adapter.accounts=accounts!!
                adapter.notifyDataSetChanged()
                Log.d("data", "$accounts")

            }
            .addOnFailureListener{exception ->

                Log.d("excetption", "${exception.message}")
            }

    }

    companion object {


        @JvmStatic
        fun newInstance() : CollectionViewFragment {
            return CollectionViewFragment()
        }
    }
}
