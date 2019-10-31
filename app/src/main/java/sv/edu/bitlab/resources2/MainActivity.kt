package sv.edu.bitlab.resources2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import sv.edu.bitlab.resources2.collectionViewComponents.CollectionViewFragment
import sv.edu.bitlab.resources2.interfaces.OnFragmentInteractionListener

class MainActivity : AppCompatActivity(),OnFragmentInteractionListener{


    private var listener:OnFragmentInteractionListener?=null
    val handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listener=this
        init()



    }

    fun init(){

        val fragment = FormFragment.newInstance()

        val builder = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment, TAG)

        builder.commit()

    }

    override fun onFragmentInteraction(index: FragmentsIndex) {


        val transaction = supportFragmentManager.beginTransaction()
        var fragment:Fragment?= null

        when(index){

            FragmentsIndex.KEY_FRAGMENT_FORM->{

                fragment= FormFragment.newInstance()
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .addToBackStack(TAG)




            }

            FragmentsIndex.KEY_FRAGMENT_COLLECTION_VIEW->{

                fragment= CollectionViewFragment.newInstance()
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .addToBackStack(TAG2)

            }


        }
        transaction
            .replace(R.id.fragment_container,fragment)
            .commit()


    }

    override fun successData() {
        Log.d("SUCCED DATA LISTENER","SUCCESDATA LISTENER")
        handler.postDelayed({
            this.runOnUiThread {
                Log.d("handler","after 3 seconds")
                listener?.onFragmentInteraction(FragmentsIndex.KEY_FRAGMENT_COLLECTION_VIEW)

            }
        }, THREE_SECONDS)
    }

}


