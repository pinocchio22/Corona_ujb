package com.example.corona_ujb.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.corona_ujb.customDialog.PharmacyDialog
import com.example.corona_ujb.customDialog.TreatDialog
import com.example.corona_ujb.DialogViewModel
import com.example.corona_ujb.R
import kotlinx.android.synthetic.main.fragment_treat.view.*

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */

class FragmentTreat : Fragment() {
    private lateinit var vm: DialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm = ViewModelProvider(this).get(DialogViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_treat, container, false)

        view.card_view6.setOnClickListener {
            openCall("tel:1339")
        }
        view.card_view7.setOnClickListener {
            openCall("tel:031-120")
        }
        view.card_view1.setOnClickListener {
            openDlg(R.drawable.coronaujb1)
        }
        view.card_view2.setOnClickListener {
            openDlg(R.drawable.coronaujb2)
        }
        view.card_view3.setOnClickListener {
            openDlg(R.drawable.coronaujb3)
        }
        view.card_view4.setOnClickListener {
            openDlg(R.drawable.coronaujb4)
        }
        view.card_view5.setOnClickListener {
            val pharmacyDlg: View = layoutInflater.inflate(R.layout.list_dialog, null)
            val dlg =
                PharmacyDialog(pharmacyDlg, vm)
            dlg.show(childFragmentManager, "pharmacyDlg")
        }
        return view.rootView
    }

    fun openDlg(ck: Int) {
        val treatDlg: View = layoutInflater.inflate(R.layout.treat_dialog, null)
        val dlg = TreatDialog(treatDlg, ck)
        dlg.show(childFragmentManager, "treatDlg")
    }

    fun openCall(str: String) {
        var intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse(str)
        startActivity(intent)
    }
}
