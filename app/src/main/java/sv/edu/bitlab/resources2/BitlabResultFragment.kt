package sv.edu.bitlab.resources2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BitlabResultFragment.OnBitlabResultFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BitlabResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BitlabResultFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listenerBitlabResult: OnBitlabResultFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_bitlab_result, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listenerBitlabResult?.onSendClickInteraction()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBitlabResultFragmentInteractionListener) {
            listenerBitlabResult = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerBitlabResult = null
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
    interface OnBitlabResultFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onSendClickInteraction()
    }

    companion object {



        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() : BitlabResultFragment {
            val params = Bundle()

            val fragment = BitlabResultFragment()
            fragment.arguments = params
            return fragment
        }
    }
}
