package com.pnit.mobile.lab4task2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_menu.*
import java.lang.RuntimeException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val websites = arrayOf(
            Website("Google", "https://www.google.com/"),
            Website("Facebook", "https://www.facebook.com/"),
            Website("Twitter", "https://twitter.com/"),
            Website("Android Developers", "https://developer.android.com/")
        )
        val adapter = ArrayAdapter(context!!, R.layout.menu_list_item, R.id.tv_website, websites.map { website ->  website.name})
        menu_listview.adapter = adapter
        menu_listview.setOnItemClickListener { parent, _, position, _ ->
            val selectedWebsiteName = parent.getItemAtPosition(position) as String
            val selectedWebsite = websites.filter { website -> website.name == selectedWebsiteName }[0]
            mListener!!.onFragmentInteraction(selectedWebsite.url)
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(result: String?)
    }
}
