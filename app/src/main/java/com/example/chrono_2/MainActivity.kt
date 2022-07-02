package com.example.chrono_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView

class MainActivity : AppCompatActivity()
{

    var copanion = various_data("init",0,0)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // create widget variables
        //var time_view = findViewById<TextView>(R.id.time)
        var money_view = findViewById<TextView>(R.id.money)
        var time_button = findViewById<Button>(R.id.PLAY_PAUSE)
        var reset_button = findViewById<Button>(R.id.RESET)
        var taimer = findViewById<Chronometer>(R.id.time)

        time_button.setOnClickListener {
            //Log.i("Current status of timer:", copanion.status_of_taimer.toString())

            if (copanion.status_of_taimer == "init")
            {
                taimer.base = SystemClock.elapsedRealtime()
                taimer.start()
                //Log.i("Start old status of timer:", copanion.status_of_taimer.toString())
                //Log.i("Taimer base:", taimer.base.toString())
                copanion.status_of_taimer = "on"
                //Log.i("Start new status of timer:",copanion.status_of_taimer.toString())
                copanion.ticker = copanion.ticker -1
            }
            else if (copanion.status_of_taimer == "on")
            {
                taimer.stop()
                //Log.i("Pause old status of timer:",copanion.status_of_taimer.toString())
                copanion.status_of_taimer = "pause"
                //Log.i("Pause new status of timer:",copanion.status_of_taimer.toString())
                copanion.base = SystemClock.elapsedRealtime() - taimer.base
                //Log.i("Taimer base:", copanion.base.toString())
                //copanion.ticker = copanion.ticker -1
            }
            else if (copanion.status_of_taimer == "pause")
            {
                //Log.i("Unpause old status of timer:",copanion.status_of_taimer.toString())
                copanion.status_of_taimer = "on"
                //Log.i("Unpause new status of timer:",copanion.status_of_taimer.toString())
                //Log.i("Copanion base init:", copanion.base.toString())
                copanion.base = SystemClock.elapsedRealtime() - copanion.base
                //Log.i("Copanion base modi:", copanion.base.toString())
                taimer.base = copanion.base
                //Log.i("Copanion base modi:", taimer.base.toString())
                taimer.start()
                copanion.ticker = copanion.ticker -2
            }
            ////time_view.set

        }
        reset_button.setOnClickListener {
            taimer.stop()
            //Log.i("Reset old status of timer:",copanion.status_of_taimer.toString())
            copanion.status_of_taimer = "init"
            copanion.base = 0
            copanion.ticker = 0
            //Log.i("Reset new status of timer:",copanion.status_of_taimer.toString())
            taimer.setText("TIME")
            money_view.setText("MONEY")
        }
        ///////////////////////////////
        taimer.setOnChronometerTickListener {
            copanion.ticker++
            money_view.text = (5*(copanion.ticker/12)).toString()
            Log.i("time view:",taimer.text.toString())
        }

    }
}

data class various_data (var status_of_taimer: String, var base: Long, var ticker: Int)
{}