package com.kanchan.logoquiz.splash

import android.app.Application
import com.google.gson.stream.JsonReader
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kanchan.logoquiz.LOGO_FILE
import com.kanchan.logoquiz.quizbank.QuestionBankService
import com.kanchan.logoquiz.quizbank.LogoPuzzleModel
import com.kanchan.logoquiz.quizbank.QuestionServiceImpl

class SplashScreeViewModel(application: Application) : AndroidViewModel(application) {

    private val questionService: QuestionBankService by lazy { QuestionServiceImpl }

    fun loadQuestionsFromAssets() {
        getApplication<Application>().assets.open(LOGO_FILE).use { inputStream ->
            JsonReader(inputStream.reader()).use { jsonReader ->
                val feedModelType = object : TypeToken<ArrayList<LogoPuzzleModel>>() {}.type
                val feedModel : ArrayList<LogoPuzzleModel> = Gson().fromJson(jsonReader, feedModelType)
                questionService.addQuestions(feedModel)
                Log.d("feedModel ","${feedModel.toString()}")
            }
        }
    }

    fun getLoaded() = questionService.arePuzzlesLoaded()
}
