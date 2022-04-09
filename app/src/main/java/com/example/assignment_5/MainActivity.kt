package com.example.assignment_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.assignment_5.data.AppDatabase

import androidx.room.Room
import com.example.assignment_5.data.History
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var db : AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "History"
        ).build()

        displayData()

        findViewById<Button>(R.id.button).setOnClickListener{
            insertData()
        }

    }

    fun insertData(){
        val run = findViewById<TextView>(R.id.DistanceRunInput).text.toString().toDouble()
        val swim = findViewById<TextView>(R.id.DistanceSwum).text.toString().toDouble()
        val calories = findViewById<TextView>(R.id.CaloriesInput).text.toString().toDouble()

        Thread{
            val hist = History(run, swim, calories)
            db.historyDao().addHistory(hist)
            displayData()
        }.start()
    }

    fun displayData(){
        Thread {
            val history = db.historyDao().getData()

            println(history)
            var totalRun: Double
            var averageRun = 0.0
            var averageSwim = 0.0
            var calories = 0.0

            for (hist in history) {
                averageRun += hist.run
                averageSwim += hist.swim
                calories += hist.calories
            }

            totalRun = averageRun
            averageRun /= history.count()
            averageSwim /= history.count()
            calories /= history.count()

            runOnUiThread{
                findViewById<TextView>(R.id.totalRun).text = totalRun.toString()
                findViewById<TextView>(R.id.averageRun).text = averageRun.toString()
                findViewById<TextView>(R.id.averageSwim).text = averageSwim.toString()
                findViewById<TextView>(R.id.averageCalories).text = calories.toString()
            }

        }.start()


    }
}