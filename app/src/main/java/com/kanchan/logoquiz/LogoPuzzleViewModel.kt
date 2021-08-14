package com.kanchan.logoquiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kanchan.logoquiz.puzzleValidator.PuzzleValidator
import com.kanchan.logoquiz.puzzleValidator.PuzzleValidatorImpl
import com.kanchan.logoquiz.quizbank.QuestionBankService
import com.kanchan.logoquiz.quizbank.LogoPuzzleModel
import com.kanchan.logoquiz.quizbank.QuestionServiceImpl

class LogoPuzzleViewModel : ViewModel() {

	private lateinit var currLogoPuzzle: LogoPuzzleModel
	val puzzleLiveData : MutableLiveData<UIState> by lazy { MutableLiveData<UIState>() }
	private val questionService: QuestionBankService by lazy { QuestionServiceImpl }
	private val puzzleValidator : PuzzleValidator by lazy { PuzzleValidatorImpl() }
	
	fun getRandomQuestion() {
		val questionModel = questionService.returnQuestion()
		questionModel?.let {
			puzzleLiveData.value = UIState.Puzzle(questionModel)
			currLogoPuzzle = questionModel
		}
	}

	fun getCurrentQuestion() : LogoPuzzleModel {
		return currLogoPuzzle
	}
	
	private fun setIsAsked(isAsked: Boolean, logoPuzzleModel: LogoPuzzleModel) {
		questionService.setIsAsked(isAsked = isAsked, logoPuzzle = logoPuzzleModel )
	}

	fun isValidAnswer(ansGiven: String, logoPuzzleModel: LogoPuzzleModel) : Boolean {
		val isValid = puzzleValidator.isValidAnswer(ansGiven, logoPuzzleModel)
		setIsAsked(isAsked = true, logoPuzzleModel = logoPuzzleModel)
		getRandomQuestion()
		return isValid
	}
}

sealed class UIState {
	data class Puzzle(val logoPuzzleModel : LogoPuzzleModel): UIState()
}
