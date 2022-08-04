package peterbach1997.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.youtube.player.YouTubeStandalonePlayer
import peterbach1997.example.myapplication.databinding.ActivityStandaloneBinding

class StandaloneActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit  var binding:ActivityStandaloneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStandaloneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnPlay.setOnClickListener(this)
        binding.btnPlaylist.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val intent =  when(v?.id) {
            R.id.btn_play -> YouTubeStandalonePlayer.createVideoIntent(this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_VIDEO_ID, 0, true, false)
            R.id.btn_playlist -> YouTubeStandalonePlayer.createPlaylistIntent(this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_PLAYLIST,0,0, true, false)
            else -> throw IllegalArgumentException("Unknown")
        }
        startActivity(intent)
    }
}