package com.kanchan.logoquiz.quizbank

import androidx.lifecycle.MutableLiveData

interface QuestionBankService  {
	fun addQuestions(questionsList: ArrayList<LogoPuzzleModel>)
	fun arePuzzlesLoaded(): MutableLiveData<Boolean>
	fun returnQuestion(): LogoPuzzleModel?
	fun setIsAsked(isAsked: Boolean, logoPuzzle: LogoPuzzleModel)
}

object QuestionServiceImpl: QuestionBankService {
	private val  puzzleLiveData  by lazy { MutableLiveData<ArrayList<LogoPuzzleModel>>() }
	private val arePuzzlesLoaded by lazy { MutableLiveData<Boolean>() }
	private lateinit var logoPuzzleList : ArrayList<LogoPuzzleModel>

	override fun addQuestions(logoPuzzles: ArrayList<LogoPuzzleModel>) {
		logoPuzzleList = logoPuzzles
		puzzleLiveData.value = logoPuzzleList
		arePuzzlesLoaded.value = true
	}

	override fun arePuzzlesLoaded(): MutableLiveData<Boolean> {
		return arePuzzlesLoaded
	}

	override fun returnQuestion(): LogoPuzzleModel? {
		return logoPuzzleList.firstOrNull { !it.isAsked }
	}

	override fun setIsAsked(isAsked: Boolean, logoPuzzle: LogoPuzzleModel) {
		logoPuzzle.isAsked = true
	}
}
