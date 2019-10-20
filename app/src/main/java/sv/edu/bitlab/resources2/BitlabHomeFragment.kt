package sv.edu.bitlab.resources2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_bitlab_home.*
import kotlinx.android.synthetic.main.fragment_bitlab_home.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BitlabHomeFragment.OnBitlabHomeFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BitlabHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BitlabHomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listenerBitlabHome: OnBitlabHomeFragmentInteractionListener? = null
    private var spinner: Spinner?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_bitlab_home, container, false)

        spinner=view.findViewById(R.id.spinner)
        spinner?.prompt="Select an Option"


        ArrayAdapter.createFromResource(
            context!!,
            R.array.string_array_name,
            R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_item)

            //simple_spinner_dropdown_item
            // Apply the adapter to the spinner
            spinner!!.adapter = adapter
        }
        view.button.setOnClickListener {

            var validationFlag=true

            if (name_edit_txt.text.isEmpty()){
                name_edit_txt.error = getString(R.string.bitlab_home_name_error)
                validationFlag=false

            }
            if (email_edit_txt.text.isEmpty()){
                    email_edit_txt.error = getString(R.string.bitlab_home_email_error)
                validationFlag=false
                }

            if(validationFlag){

                listenerBitlabHome?.onSendClickListener()
            }else{
                Snackbar.make(it, "Some fields are required", Snackbar.LENGTH_LONG)
                    .setAction("OK", {  }).show()

            }


            }




        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listenerBitlabHome?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBitlabHomeFragmentInteractionListener) {
            listenerBitlabHome = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerBitlabHome = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnBitlabHomeFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
        fun onSendClickListener()
    }

    companion object {



        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() : BitlabHomeFragment {
            val params = Bundle()

            val fragment = BitlabHomeFragment()
            fragment.arguments = params
            return fragment
        }
    }
}
