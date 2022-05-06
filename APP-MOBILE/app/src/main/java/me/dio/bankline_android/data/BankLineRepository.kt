package me.dio.bankline_android.data

import android.util.Log
import androidx.lifecycle.liveData
import me.dio.bankline_android.data.remote.BankLineApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

object BankLineRepository {

    private val TAG = javaClass.simpleName

    private val restApi by lazy {
        Retrofit.Builder()
        .baseUrl("https://anderson-diosantander-dev-week.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BankLineApi::class.java)
    }

    fun findBankStatement(accountHolderId: Int) = liveData {
        emit(State.Wait)
        try {
           emit(State.Success(data = restApi.findBankStatement(accountHolderId)))
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            emit(State.Error(e.message))
        }
    }
}