package uz.itschool.test_app

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import uz.akbar.mode.Test

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var tests = arrayListOf<Test>()
    var list_of_questions = ArrayList<Int>()
    var count = 0
    var pos: Boolean = false
    var index = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list_of_questions.add(-1)
        list_of_questions.add(-1)
        list_of_questions.add(-1)
        list_of_questions.add(-1)
        tests.add(Test("Which element is found more in Earth?","Nitrogen","Oxygen","Hydrogen","Oxygen"))
        tests.add(Test("What is the diameter of Sun?","1,392,684 km","145,263,987 km","2,598,745 km","1,392,684 km"))
        tests.add(Test("At how much speed Moon moves across the Sun?","4,250 km per hour","250 km per hour","2,250 km per hour","2,250 km per hour"))
        tests.add(Test("Solve the problem:  1+0=?","1","0","10","1"))

        createNumber(tests.size)
        createTest(index)

        next.setOnClickListener {
            val checkedRadioButtonId = radio_group.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
            when (radio_group.checkedRadioButtonId) {
                -1 -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
                else -> {
                    if (tests[index].correct_answer == selectedRadioButton.text && !pos && index<tests.size){
                        if(index==tests.size-1){
                            pos= true
                        }
                        count++
                    }
                }
            }

            if (radio_group.checkedRadioButtonId != null) {
                setJavob(index, radio_group.checkedRadioButtonId)
            }

            if (index<tests.size - 1) {
                index++
            }
            createTest(index)
            if (index==tests.size - 1){
                button_finish.visibility = View.VISIBLE
                next.visibility = View.GONE
                prev_button.visibility = View.GONE
            }

        }
        prev_button.setOnClickListener {
            val checkedRadioButtonId = radio_group.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
            if (radio_group.checkedRadioButtonId!=null) {
                setJavob(index, radio_group.checkedRadioButtonId)
            }
            if (index!=0){
                --index
            }
            createTest(index)

        }

        button_finish.setOnClickListener {
            val checkedRadioButtonId = radio_group.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
            if (tests[index].correct_answer == selectedRadioButton.text && !pos && index<tests.size){
                if(index==tests.size-1){
                    pos=true
                }
                count++
            }
            text_for_score.visibility=View.VISIBLE
            score.visibility=View.VISIBLE
            card.visibility=View.VISIBLE
            score.visibility = View.VISIBLE
            score.text = count.toString()



        }

    }


    fun setJavob(ind: Int, q: Int) {
        list_of_questions[ind] = q
    }

    fun createTest(n: Int) {
        var test = tests[n]
        radio_group.check(-1)
        text_question.text = test.question
        answer_1.text = test.answer1
        answer_2.text = test.answer2
        answer_3.text = test.answer3
        if (list_of_questions[n] != -1) {
            radio_group.check(list_of_questions[n])
        }
        createNumber(4)



    }
    fun createNumber(n: Int) {
        linear_layout_question_number.removeAllViews()
        for (i in 0 until n) {
            var btn = Button(this)
            btn.id = i
            btn.text = "${i + 1}"
            btn.tag = "$i"
            if (list_of_questions[i] != -1) {
                btn.setBackgroundResource(R.drawable.btn_bg)
                btn.setTextColor(Color.WHITE)
            }
            btn.setOnClickListener(this)

            linear_layout_question_number.addView(btn)
        }
    }


    override fun onClick(p0: View?) {
        var btn = findViewById<Button>(p0!!.id)
        if (radio_group.checkedRadioButtonId != -1) setJavob(index, radio_group.checkedRadioButtonId)
        index = btn.tag.toString().toInt()
        createTest(index)

        if (index != tests.size - 1){
            next.visibility = View.VISIBLE
            button_finish.visibility = View.GONE
            prev_button.visibility = View.VISIBLE
        }
        else if (index == tests.size - 1) {
            next.visibility = View.GONE
            button_finish.visibility = View.VISIBLE
        }
    } }