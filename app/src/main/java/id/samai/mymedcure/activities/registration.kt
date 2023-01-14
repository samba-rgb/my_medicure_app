package id.samai.mymedcure.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.samai.mymedcure.R
import id.samai.mymedcure.models.userdown

class registration : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        auth = FirebaseAuth.getInstance()
        val uid =  auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("csv_data")
findViewById<Button>(R.id.button_reg)?.setOnClickListener {
      val firstname = findViewById<EditText>(R.id.name_regis).text
    val version = 0
    val link = "dont know"
    val user = userdown(firstname.toString(),version,link)

if (uid!= null){
    databaseReference.child(uid).setValue(user).addOnCompleteListener{
        if(it.isSuccessful){

        }
        else{

            Toast.makeText(this@registration,"failed bro",Toast.LENGTH_SHORT).show()
        }
    }
}
}
    }
}