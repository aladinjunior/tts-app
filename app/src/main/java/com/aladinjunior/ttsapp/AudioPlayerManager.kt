import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

object AudioPlayerManager {

    private var mediaPlayer: MediaPlayer? = null

    fun buildMediaPlayerInstance(): MediaPlayer {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }
        return mediaPlayer!!
    }

    fun prepareMedia(url: String, onPrepared: () -> Unit) {
        try {
            mediaPlayer?.reset()
            mediaPlayer?.setDataSource(url)

            mediaPlayer?.prepareAsync()

            mediaPlayer?.setOnPreparedListener {
                onPrepared()
            }

        } catch (e: IOException) {
            Log.d("MediaPlayerManager", "prepareMedia: ${e.message}")
        } catch (e: IllegalStateException) {
            Log.d("MediaPlayerManager", "IllegalStateException: ${e.message}")
        }
    }

    fun playMedia(
        onCompleted: () -> Unit,
    ) {
        mediaPlayer?.start()
        mediaPlayer?.setOnCompletionListener {
            onCompleted()
        }
    }


    fun stopMedia() {

        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
        }


    }

    fun releaseInstance() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}