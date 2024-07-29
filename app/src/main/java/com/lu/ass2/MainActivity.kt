package com.lu.ass2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lu.ass2.dialog.MovieDialogFrag
import com.lu.ass2.frag.MovieFrag
import com.lu.ass2.frag.MusicFrag

class MainActivity : AppCompatActivity() {

    private lateinit var selectionTV: TextView
    private lateinit var feedbackTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.popBackStack()

        val movieBtn: Button = findViewById(R.id.fragMovieBtn)
        selectionTV = findViewById(R.id.selectionTV)
        feedbackTV = findViewById(R.id.feedbackTV)

        val movieFrag = MovieFrag()
        val musicFrag = MusicFrag()
        movieBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameHolderMain, MovieFrag())
                .addToBackStack(null)
                .remove(musicFrag)
                .commit()
        }
        val musicBtn: Button = findViewById(R.id.fragMusicBtn)
        musicBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameHolderMain, MusicFrag())
                .addToBackStack(null)
                .remove(movieFrag)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_ass, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.item1 -> {
                showToast("Item 1")
            }

            R.id.movieItem -> {
                showToast("Movie Rating")
                val dialog = MovieDialogFrag()
                dialog.show(supportFragmentManager, "Move Rating")
            }

            R.id.musicItem -> {
                showToast("Item 3")
            }
        }
        return true
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun updateFeedback(selectText: CharSequence, feedbackText: CharSequence) {
        selectionTV.text = selectText
        feedbackTV.text = feedbackText
    }
}
