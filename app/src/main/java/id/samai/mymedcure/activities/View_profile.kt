package id.samai.mymedcure.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import id.samai.mymedcure.R
import id.samai.mymedcure.helpers.UPDATE_ACTION
import id.samai.mymedcure.models.firebase_regis
import kotlinx.android.synthetic.main.card_profile.*

class View_profile : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_profile)

val img =  findViewById<ImageView>(R.id.view_profile_img)
        val name = findViewById<TextView>(R.id.view_name)
        val gmail = findViewById<TextView>(R.id.view_user_gmail)

        val dob = findViewById<TextView>(R.id.view_user_dob)
        val blood = findViewById<TextView>(R.id.view_user_blood)
        val flat = findViewById<TextView>(R.id.view_flat_address)
        val area = findViewById<TextView>(R.id.view_area_street_address)
        val town = findViewById<TextView>(R.id.view_Town_address)
        val state = findViewById<TextView>(R.id.view_State_addess)
        val emergency_name = findViewById<EditText>(R.id.View_FullName_address_emergency)
        val emergency_phone =  findViewById<EditText>(R.id.View_Mobile_address_emergency)
        val emergency_flat = findViewById<EditText>(R.id.View_flat_address_emergency)
        val emergency_area = findViewById<EditText>(R.id.View_area_street_address_emergency)
        val emergency_town = findViewById<EditText>(R.id.View_Town_address_emergency)
        val emergency_state = findViewById<EditText>(R.id.View_State_addess_emergency)
        val emergency_pincode = findViewById<EditText>(R.id.View_Pincode_addess_emergency)
        auth = FirebaseAuth.getInstance()

        val uid = auth.currentUser?.uid




        databaseReference = FirebaseDatabase.getInstance().getReference("csv_data2")

        val addValueEventListener = databaseReference.child(uid.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user2 = snapshot.getValue(firebase_regis::class.java)!!
                    name.text = user2.name
                    gmail.text = user2.gmail
                    dob.text = user2.dob
                    blood.text = user2.bloodgroup
                    flat.text =  user2.flat
                    area.text = user2.area
                    town.text =  user2.town
                    state.text = user2.state
                    emergency_name.setText(user2.emergency_name)
                    emergency_phone.setText(user2.emergency_phone)
                    emergency_flat.setText(user2.emergency_flat)
                    emergency_area.setText(user2.emergency_area)
                    emergency_town.setText(user2.emergency_town)
                    emergency_state.setText(user2.emergency_state)
                    emergency_pincode.setText(user2.emergency_pincode)

                    Toast.makeText(this@View_profile, user2.town, Toast.LENGTH_SHORT).show()

                    Glide.with(applicationContext).load(user2.linkimg).into(img)


                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@View_profile, "problem araised", Toast.LENGTH_SHORT)
                        .show()
                }
            })

      val   personalinfo = findViewById<LinearLayout>(R.id.view_user_info_under)
      val  experience = findViewById<LinearLayout>(R.id.view_experience_under)
      val  review = findViewById<LinearLayout>(R.id.view_review_under)
       val personalinfobtn = findViewById<TextView>(R.id.view_user_info)
       val  experiencebtn = findViewById<TextView>(R.id.view_emergency)
       val  reviewbtn = findViewById<TextView>(R.id.view_review)
        /*making personal info visible*/
        /*making personal info visible*/personalinfo.setVisibility(View.VISIBLE)
        experience.setVisibility(View.GONE)
        review.setVisibility(View.GONE)


        personalinfobtn.setOnClickListener(View.OnClickListener {
            personalinfo.setVisibility(View.VISIBLE)
            experience.setVisibility(View.GONE)
            review.setVisibility(View.GONE)
            personalinfobtn.setTextColor(resources.getColor(R.color.blue))
            experiencebtn.setTextColor(resources.getColor(R.color.grey))
            reviewbtn.setTextColor(resources.getColor(R.color.grey))
        })

        experiencebtn.setOnClickListener(View.OnClickListener {
            personalinfo.setVisibility(View.GONE)
            experience.setVisibility(View.VISIBLE)
            review.setVisibility(View.GONE)
            personalinfobtn.setTextColor(resources.getColor(R.color.grey))
            experiencebtn.setTextColor(resources.getColor(R.color.blue))
            reviewbtn.setTextColor(resources.getColor(R.color.grey))
        })

        reviewbtn.setOnClickListener(View.OnClickListener {
            personalinfo.setVisibility(View.GONE)
            experience.setVisibility(View.GONE)
            review.setVisibility(View.VISIBLE)
            personalinfobtn.setTextColor(resources.getColor(R.color.grey))
            experiencebtn.setTextColor(resources.getColor(R.color.grey))
            reviewbtn.setTextColor(resources.getColor(R.color.blue))
        })
        findViewById<LinearLayout>(R.id.edit_profile).setOnClickListener(View.OnClickListener{
            Intent(it.context, FireBase_edit_ui::class.java).apply {
                val bundle = Bundle().apply {

                }
                action = UPDATE_ACTION
                putExtras(bundle)
                it.context.startActivity(this)
            }

        })
    }
}