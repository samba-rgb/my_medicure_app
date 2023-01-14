package id.samai.mymedcure.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import id.samai.mymedcure.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        loginbtn.setOnClickListener {

            val etLogin = etEmail.text.toString()
            val etPassword = etPassword.text.toString()

            auth.signInWithEmailAndPassword(etLogin,etPassword)
                    .addOnCompleteListener(this){

                        if (it.isSuccessful){

                            finish()

                        }else{

                            Toast.makeText(this,"Login Failed", Toast.LENGTH_SHORT).show()

                        }

                    }

        }


    }
/*
    override fun onStart() {

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if(currentUser != null){
            val i = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(i)
        }
        super.onStart()
    }


 */
}