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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_form.*
import kotlinx.android.synthetic.main.fragment_form.view.*
import com.google.firebase.storage.StorageReference
import sv.edu.bitlab.resources2.interfaces.OnFragmentInteractionListener

import android.content.ContentResolver
import android.widget.AdapterView

import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task

import com.google.firebase.storage.UploadTask
import sv.edu.bitlab.resources2.models.Account


class FormFragment : Fragment(), AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       val item= parent?.getItemAtPosition(position)
        Log.d("spinner","the selected item is -> $item")


    }

    private var mStorageRef: StorageReference? = null
    private var fireStore=FirebaseFirestore.getInstance()

    private var listener:OnFragmentInteractionListener?=null
    private var spinner: Spinner?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mStorageRef=FirebaseStorage.getInstance().getReference("accounts-image")



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_form, container, false)

        spinner=view.findViewById(R.id.spinner_options)
        spinner?.onItemSelectedListener = this
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



        view.btn_send.setOnClickListener {

            var validationFlag=true

            if (edit_txt_name.text.isEmpty()){
                edit_txt_name.error = getString(R.string.form_fragment_name_error)
                validationFlag=false

            }
            if (edit_txt_email.text.isEmpty()){
                    edit_txt_email.error = getString(R.string.form_fragment_email_error)
                validationFlag=false
                }

            if(validationFlag){

                   publishImage(view)
                view.form_fragment_layout.visibility=View.GONE
                view.form_fragment_result_layout.visibility=View.VISIBLE
                listener?.successData()

            }else{
                Snackbar.make(it, "Some fields are required", Snackbar.LENGTH_LONG)
                    .setAction("OK") {  }.show()

            }





            }

        view.txt_check_collection.setOnClickListener{

            listener?.onFragmentInteraction(FragmentsIndex.KEY_FRAGMENT_COLLECTION_VIEW)

        }




        return view
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


    fun publishImage(view: View) {

        //val imagePath="android.resource://sv.edu.bitlab.resources2/drawable/github_neko"
        //val file = Uri.fromFile(File(imagePath))

        val uri=getUriFromDrawable(context!!,R.drawable.github_neko)

        Log.d("IMAGE URI","The URI is -> $uri")
        val imageRef = mStorageRef?.child("${System.currentTimeMillis()}_github_neko")

       val  uploadTask = imageRef?.putFile(uri)


        uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation imageRef.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val  downloadUriPath = task.result


                    val account=Account(view.edit_txt_name.text.toString(),
                        view.edit_txt_email.text.toString(),
                        view.edit_txt_telephone?.text.toString(),
                        spinner?.selectedItem.toString(),downloadUriPath.toString())




                    Log.d("Account","The object Account -> $account")
                   pushToDatabase(account)


                }
            }


    }

    fun  pushToDatabase(account: Account){


        fireStore.collection("accounts")
            .add(account)
            .addOnSuccessListener {document->
                Log.d("TEST","DocumentSnapshot added with ID: ${document.id} "
                )
            }
            .addOnFailureListener{exception->
                Log.d("TEST", "Error adding document ${exception.message}")
            }



    }

     fun getUriFromDrawable(context:Context, drawableId:Int):Uri {
         return Uri.parse(
             ContentResolver.SCHEME_ANDROID_RESOURCE +
             "://" + context.resources.getResourcePackageName(drawableId)
             + '/'.toString() + context.resources.getResourceTypeName(drawableId)
             + '/'.toString() + context.resources.getResourceEntryName(drawableId)
         )


}


    companion object {




        @JvmStatic
        fun newInstance() : FormFragment {
            return FormFragment()
        }
    }
}
