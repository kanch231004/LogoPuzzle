package com.kanchan.logoquiz.puzzleValidator

import com.kanchan.logoquiz.quizbank.LogoPuzzleModel

class PuzzleValidatorImpl: PuzzleValidator {

    override fun isValidAnswer(ansGiven: String, logoPuzzleModel: LogoPuzzleModel): Boolean {
       return ansGiven == logoPuzzleModel.name
    }
}

interface PuzzleValidator {
    fun isValidAnswer(ansGiven: String, logoPuzzleModel: LogoPuzzleModel): Boolean
}
