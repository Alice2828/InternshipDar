package com.example.tictactoe

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment

enum class Turn {
    USER1,
    USER2
}

class GameFragment : Fragment() {
    private lateinit var box1: ImageView
    private lateinit var box2: ImageView
    private lateinit var box3: ImageView
    private lateinit var box4: ImageView
    private lateinit var box5: ImageView
    private lateinit var box6: ImageView
    private lateinit var box7: ImageView
    private lateinit var box8: ImageView
    private lateinit var box9: ImageView
    private lateinit var mode: Turn

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_game, container, false)
        mode = Turn.USER1
        initViews(rootView)
        clickLogic()
        return rootView
    }

    private fun initViews(rootView: View) {
        box1 = rootView.findViewById(R.id.box1)
        box2 = rootView.findViewById(R.id.box2)
        box3 = rootView.findViewById(R.id.box3)
        box4 = rootView.findViewById(R.id.box4)
        box5 = rootView.findViewById(R.id.box5)
        box6 = rootView.findViewById(R.id.box6)
        box7 = rootView.findViewById(R.id.box7)
        box8 = rootView.findViewById(R.id.box8)
        box9 = rootView.findViewById(R.id.box9)
    }

    private fun clickLogic() {
        box1.setOnClickListener {
            val dialog = FireMissilesDialogFragment(box1)
            activity?.supportFragmentManager?.let { it1 -> dialog.show(it1, "Choose the option") }
        }
    }

    class FireMissilesDialogFragment(var box: ImageView) : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                // Use the Builder class for convenient dialog construction
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("X"
                    ) { _, _ ->
                        box.setImageResource(R.drawable.ex)
                    }
                    setNegativeButton("O"
                    ) { _, _ ->
                        box.setImageResource(R.drawable.zero)
                    }
                }
                builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }
}