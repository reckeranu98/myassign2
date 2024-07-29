package com.lu.ass2.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.DialogFragment
import com.lu.ass2.MainActivity
import com.lu.ass2.R


class MovieDialogFrag : DialogFragment() {
    private val options = arrayListOf(NO_SELECT, AVENGERS, X_MEN, HARRY_POTTER, BATMAN, SPIDER_MAN)

    companion object {
        const val NO_SELECT = "Please Select"
        const val AVENGERS = "Avengers"
        const val X_MEN = "X-Men"
        const val HARRY_POTTER = "Harry Potter"
        const val BATMAN = "Batman"
        const val SPIDER_MAN = "Spider Man"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.movie_feedback_dialog, container, false)

        val radioGroup: RadioGroup = rootView.findViewById(R.id.movieRadioGrp)
        val confirmBtn: Button = rootView.findViewById(R.id.confirmBtn)

        val movieSelector: Spinner = rootView.findViewById(R.id.spMovie)

        val arrayAdapter = ArrayAdapter(
            rootView.context,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            options
        )
        var selectionOpt = NO_SELECT
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        movieSelector.adapter = arrayAdapter
        movieSelector.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val selectedValue = options[position]
                selectionOpt = selectedValue
                when (selectedValue) {
                    NO_SELECT -> radioGroup.visibility = View.INVISIBLE
                    AVENGERS, X_MEN, HARRY_POTTER, BATMAN, SPIDER_MAN ->
                        radioGroup.visibility = View.VISIBLE

                    else -> radioGroup.visibility = View.INVISIBLE
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        confirmBtn.setOnClickListener {
            val selectedRadioId = radioGroup.checkedRadioButtonId
            val selectedRB: RadioButton = rootView.findViewById(selectedRadioId)
            val parentActivity = activity as MainActivity
            parentActivity.updateFeedback(selectionOpt, selectedRB.text.toString())
            dismiss()
        }

        val dismissBtn: Button = rootView.findViewById(R.id.dismissBtn)
        dismissBtn.setOnClickListener {
            dismiss()
        }

        return rootView
    }

}
