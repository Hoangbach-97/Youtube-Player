package peterbach1997.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "whtHwHRqRl0"
const val YOUTUBE_PLAYLIST = "PLNLAXkDustqG-xFb3bTsMLsXs7iSaGNim"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val TAG = "Youtube"
    private val DIALOG_REQUEST_CODE = 1
    private val playerView by lazy {  YouTubePlayerView(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: **************CALLED***********")
        super.onCreate(savedInstanceState)
        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layout.addView(playerView)
        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        Log.d(TAG, "onInitializationSuccess: ****************CALLED*******")

        Log.d(TAG, "onInitializationSuccess: ****************$p2*******")
        p1?.setPlaybackEventListener(playbackEventListener())
        p1?.setPlayerStateChangeListener(playerStateListener())
        if (!p2) {
            p1?.loadVideo(YOUTUBE_VIDEO_ID)
        } else {
            p1?.play()
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Log.d(TAG, "onInitializationFailure: *************CALLED*********")
        if (p1?.isUserRecoverableError == true) {
            Log.d(TAG, "onInitializationFailure: ************FAILED_SERVICE**********")
            p1.getErrorDialog(this, DIALOG_REQUEST_CODE).show()
        } else {
            Log.d(TAG, "onInitializationFailure: **************UNKNOWN_ERROR*********")
            Toast.makeText(this, "ERROR: $p1", Toast.LENGTH_LONG).show()
        }
    }

    private fun playbackEventListener() = object : YouTubePlayer.PlaybackEventListener {
        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity, "Playing", Toast.LENGTH_LONG).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity, "Paused", Toast.LENGTH_LONG).show()
        }

        override fun onStopped() {
            Toast.makeText(this@YoutubeActivity, "Stopped", Toast.LENGTH_LONG).show()
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onSeekTo(p0: Int) {
        }

    }

    private fun playerStateListener() = object : YouTubePlayer.PlayerStateChangeListener {
        override fun onLoading() {
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity, "Add video", Toast.LENGTH_LONG).show()
        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity, "Video has started", Toast.LENGTH_LONG).show()

        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity, "You have completed video", Toast.LENGTH_LONG)
                .show()

        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "onActivityResult: ********* $requestCode")
        Log.d(TAG, "onActivityResult: ********* $resultCode")
        Log.d(TAG, "onActivityResult: ********* $data")
        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }
}