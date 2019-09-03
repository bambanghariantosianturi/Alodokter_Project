package com.androidexercise.admin.alodokterproject.fragment


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.androidexercise.admin.alodokterproject.R
import com.androidexercise.admin.alodokterproject.screen.LoginActivity
import com.androidexercise.admin.alodokterproject.util.Config
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlin.system.exitProcess
import android.view.Menu
import android.view.MenuItem


/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = context?.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        var nmUsr = sharedPreferences?.getString(Config.NAME_USER_SHARED, "")
        var emUsr = sharedPreferences?.getString(Config.EMAIL_USER_SHARED, "")

        tv_name_profile.text = nmUsr.toString()
        tv_email.text = emUsr.toString()
        tv_number_phone.text = "081260763049"

        Glide.with(context!!)
            .load(resources.getDrawable(R.drawable.ic_account_circle_black_24dp))
            .into(iv_pict)
    }
}
