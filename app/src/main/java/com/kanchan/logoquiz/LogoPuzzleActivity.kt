package com.kanchan.logoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kanchan.logoquiz.databinding.ActivityLogoPuzzleBinding
import com.kanchan.logoquiz.quizbank.LogoPuzzleModel

class LogoPuzzleActivity : AppCompatActivity() {
	private lateinit var logoBinding: ActivityLogoPuzzleBinding
	private lateinit var logoViewModel: LogoPuzzleViewModel
	
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
		logoBinding = ActivityLogoPuzzleBinding.inflate(layoutInflater)
        setContentView(logoBinding.root)
		logoViewModel = ViewModelProvider(this).get(LogoPuzzleViewModel::class.java)
		observePuzzles()
		logoViewModel.getRandomQuestion()
		/*For simplicity we are validating correct ans by an editext field
		* */
		logoBinding.btnSubmit.setOnClickListener {
			if (logoViewModel.isValidAnswer(ansGiven = logoBinding.etAnswer.text.toString(),
					logoPuzzleModel = logoViewModel.getCurrentQuestion())) {
				Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
			} else {
				Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
			}
		}
    }
	
	private fun observePuzzles() {
		logoViewModel.puzzleLiveData.observe(this) { uiState ->
			when(uiState) {
				is UIState.Puzzle -> displayPuzzle(uiState.logoPuzzleModel)
			}
		}
	}

	private fun displayPuzzle(logoPuzzleModel: LogoPuzzleModel) {
		Glide.with(logoBinding.root.context).load(logoPuzzleModel.imgUrl).into(logoBinding.ivImage)
	}
}
