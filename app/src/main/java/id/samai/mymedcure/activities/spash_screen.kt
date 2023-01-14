package id.samai.mymedcure.activities

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth
import id.samai.mymedcure.R

class spash_screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spash_screen)

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        /*window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,

        )

         */
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        /*
        val animationView = findViewById<LottieAnimationView>(R.id.animation_view2)
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator
                .addUpdateListener { animation: ValueAnimator ->
                    animationView
                            .setProgress(
                                    animation
                                            .animatedValue as Float
                            )
                }
        animator.start()


         */
        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }
}