package peterbach1997.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import peterbach1997.example.myapplication.databinding.ActivityMainBinding
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSingle.setOnClickListener(this)
        binding.btnStandalone.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        val intent = when (v.id) {
            R.id.btn_single -> Intent(this, YoutubeActivity::class.java)
            R.id.btn_standalone -> Intent(this, StandaloneActivity::class.java)
            else -> throw IllegalArgumentException("Unknown")
        }
        startActivity(intent)

    }
}