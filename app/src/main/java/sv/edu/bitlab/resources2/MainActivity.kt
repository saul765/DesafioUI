package sv.edu.bitlab.resources2

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(),BitlabHomeFragment.OnBitlabHomeFragmentInteractionListener,BitlabResultFragment.OnBitlabResultFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSendClickInteraction() {
        val fragment = BitlabResultFragment.newInstance()

        val builder = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment, TAG2)
         .addToBackStack(TAG2)
        builder.commitAllowingStateLoss()
    }

    override fun onSendClickListener() {

        val fragment = BitlabResultFragment.newInstance()

        val builder = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment, TAG)
        .addToBackStack(TAG)
        builder.commitAllowingStateLoss()

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fragment = BitlabHomeFragment.newInstance()

        val builder = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment, TAG)
        // .addToBackStack(MainActivity.FRAGMENT_TAG4)
        builder.commitAllowingStateLoss()


    }
}


