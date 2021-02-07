package com.example.tictactoe

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tictactoe.model.User
import com.example.tictactoe.model.UserList


enum class Turn {
    USER1,
    USER2,
    WIN1,
    WIN2,
    NOBODY
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
    private lateinit var whoIs: TextView
    private lateinit var user1: User
    private lateinit var user2: User
    private lateinit var continuE: Button
    private val args: GameFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_game, container, false)
        getUsers()
        mode = Turn.USER1
        initViews(rootView)
        whoIs.text = user1.userName
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
        whoIs = rootView.findViewById(R.id.turn)
        continuE = rootView.findViewById(R.id.continuE)
        continuE.setOnClickListener {
            val action = GameFragmentDirections.actionGameToRecords(user1.userName, user2.userName)
            rootView.findNavController().navigate(action)
        }
    }

    private fun clickLogic() {
        box1.setOnClickListener {
            chooseXorO(box1)
        }
        box2.setOnClickListener {
            chooseXorO(box2)
        }
        box3.setOnClickListener {
            chooseXorO(box3)
        }
        box4.setOnClickListener {
            chooseXorO(box4)
        }
        box5.setOnClickListener {
            chooseXorO(box5)
        }
        box6.setOnClickListener {
            chooseXorO(box6)
        }
        box7.setOnClickListener {
            chooseXorO(box7)
        }
        box8.setOnClickListener {
            chooseXorO(box8)
        }
        box9.setOnClickListener {
            chooseXorO(box9)
        }
    }

    private fun getUsers() {
        user1 = UserList.findByName(args.userName1)!!
        user2 = UserList.findByName(args.userName2)!!
    }

    private fun chooseXorO(box: ImageView) {
        if (mode == Turn.USER1) {
            val myImage = context?.let { getDrawable(it, R.drawable.ex) }
            box.setImageDrawable(myImage)
            box.isEnabled = false
        }
        if (mode == Turn.USER2) {
            val myImage = context?.let { getDrawable(it, R.drawable.zero) }
            box.setImageDrawable(myImage)
            box.isEnabled = false
        }
        decideWinner()
    }

    private fun decideWinner() {
        if (!checkForWin()) {
            if (mode == Turn.USER1) {
                mode = Turn.USER2
                whoIs.text = user2.userName
            } else if (mode == Turn.USER2) {
                mode = Turn.USER1
                whoIs.text = user1.userName
            } else if (mode == Turn.NOBODY) {
                Toast.makeText(context, "Nobody is a winner!", Toast.LENGTH_LONG).show()
                continuE.visibility = View.VISIBLE
            }
        } else {
            if (mode == Turn.USER1) {
                mode = Turn.WIN1
                Toast.makeText(context, "${user1.userName} is a winner!", Toast.LENGTH_LONG).show()
                UserList.getUserList().remove(user1)
                user1.increaseWin()
                UserList.getUserList().add(user1)
                UserList.getUserList().remove(user2)
                user2.increaseLose()
                UserList.getUserList().add(user2)

            } else
                if (mode == Turn.USER2) {
                    mode = Turn.WIN2
                    Toast.makeText(context, "${user2.userName} is a winner!", Toast.LENGTH_LONG).show()
                    UserList.getUserList().remove(user2)
                    user2.increaseWin()
                    UserList.getUserList().add(user2)
                    UserList.getUserList().remove(user1)
                    user1.increaseLose()
                    UserList.getUserList().add(user1)
                }
            continuE.visibility = View.VISIBLE
        }
    }

    private fun checkForWin(): Boolean {
        val image1 = box1.drawable.constantState
        val image2 = box2.drawable.constantState
        val image3 = box3.drawable.constantState
        val image4 = box4.drawable.constantState
        val image5 = box5.drawable.constantState
        val image6 = box6.drawable.constantState
        val image7 = box7.drawable.constantState
        val image8 = box8.drawable.constantState
        val image9 = box9.drawable.constantState
        val blank = context?.let { getDrawable(it, R.drawable.blank)?.constantState }

        val oneCombo = image1?.equals(image2) == true && image2?.equals(image3) == true && image2 != blank
        val twoCombo = image4?.equals(image5) == true && image5?.equals(image6) == true && image4 != blank
        val threeCombo = image7?.equals(image8) == true && image8?.equals(image9) == true && image7 != blank
        val fourCombo = image1?.equals(image4) == true && image4?.equals(image7) == true && image4 != blank
        val fiveCombo = image2?.equals(image5) == true && image5?.equals(image8) == true && image2 != blank
        val sixCombo = image3?.equals(image6) == true && image6?.equals(image9) == true && image3 != blank

        val sevenCombo = image1?.equals(image5) == true && image5?.equals(image9) == true && image1 != blank
        val eightCombo = image3?.equals(image5) == true && image5?.equals(image7) == true && image3 != blank
        if (image1 != blank && image2 != blank && image3 != blank && image4 != blank && image5 != blank && image6 != blank && image7 != blank && image8 != blank && image9 != blank)
            mode = Turn.NOBODY

        if (oneCombo) {
            box1.setBackgroundColor(Color.RED)
            box2.setBackgroundColor(Color.RED)
            box3.setBackgroundColor(Color.RED)
        }
        if (twoCombo) {
            box4.setBackgroundColor(Color.RED)
            box5.setBackgroundColor(Color.RED)
            box6.setBackgroundColor(Color.RED)
        }
        if (threeCombo) {
            box7.setBackgroundColor(Color.RED)
            box8.setBackgroundColor(Color.RED)
            box9.setBackgroundColor(Color.RED)
        }
        if (fourCombo) {
            box1.setBackgroundColor(Color.RED)
            box4.setBackgroundColor(Color.RED)
            box7.setBackgroundColor(Color.RED)
        }
        if (fiveCombo) {
            box2.setBackgroundColor(Color.RED)
            box5.setBackgroundColor(Color.RED)
            box8.setBackgroundColor(Color.RED)
        }
        if (sixCombo) {
            box3.setBackgroundColor(Color.RED)
            box6.setBackgroundColor(Color.RED)
            box9.setBackgroundColor(Color.RED)
        }
        if (sevenCombo) {
            box1.setBackgroundColor(Color.RED)
            box5.setBackgroundColor(Color.RED)
            box9.setBackgroundColor(Color.RED)
        }
        if (eightCombo) {
            box3.setBackgroundColor(Color.RED)
            box5.setBackgroundColor(Color.RED)
            box7.setBackgroundColor(Color.RED)
        }

        return oneCombo || twoCombo || threeCombo || fourCombo || fiveCombo || sixCombo || sevenCombo || eightCombo
    }
}