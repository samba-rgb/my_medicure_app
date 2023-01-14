package id.samai.mymedcure.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.cazaea.sweetalert.SweetAlertDialog
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import id.samai.mymedcure.R
import id.samai.mymedcure.helpers.UPDATE_ACTION
import id.samai.mymedcure.models.firebase_regis

class FireBase_edit_ui : AppCompatActivity() {
    //URI
    private val PICK_PDF_REQUEST = 2342
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    private var mainImageURI: Uri? = null

    //REQUEST CODE
    val REQUEST = 1

    val FUCK_UP = 2

    private lateinit var auth : FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_base_edit_ui)
        val progrss =  findViewById<FrameLayout>(R.id.loadingFrame)

        val   personalinfo = findViewById<LinearLayout>(R.id.personalinfo_edit)
        val  experience = findViewById<LinearLayout>(R.id.experience_edit)
        val  review = findViewById<LinearLayout>(R.id.review_edit)
        val personalinfobtn = findViewById<TextView>(R.id.personalinfobtn_edit)
        val  experiencebtn = findViewById<TextView>(R.id.experiencebtn_edit)
        val  reviewbtn = findViewById<TextView>(R.id.reviewbtn_edit)
        /*making personal info visible*/
        /*making personal info visible*/personalinfo.setVisibility(View.VISIBLE)
        if (intent.action == UPDATE_ACTION){
            val img = findViewById<ImageView>(R.id.profile_icon1)
            val firstname = findViewById<EditText>(R.id.user_name1)
            val gmail = findViewById<EditText>(R.id.user_gmail1)
            val dob = findViewById<EditText>(R.id.user_dob1)
            val blood = findViewById<EditText>(R.id.user_bloodgroup1)
            val phone =  findViewById<EditText>(R.id.user_phone1)
            val flat = findViewById<EditText>(R.id.flat_address)
            val area =  findViewById<EditText>(R.id.area_street_address)
            val town  = findViewById<EditText>(R.id.Town_address)
            val state  =  findViewById<EditText>(R.id.State_addess)
            val emergency_name = findViewById<EditText>(R.id.FullName_address_emergency)
            val emergency_phone =  findViewById<EditText>(R.id.Mobile_address_emergency)
            val emergency_flat = findViewById<EditText>(R.id.flat_address_emergency)
            val emergency_area = findViewById<EditText>(R.id.area_street_address_emergency)
            val emergency_town = findViewById<EditText>(R.id.Town_address_emergency)
            val emergency_state = findViewById<EditText>(R.id.State_addess_emergency)
            val emergency_pincode = findViewById<EditText>(R.id.Pincode_addess_emergency)
            auth = FirebaseAuth.getInstance()

            val uid = auth.currentUser?.uid

            databaseReference = FirebaseDatabase.getInstance().getReference("csv_data2")

            val addValueEventListener = databaseReference.child(uid.toString())
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val user2 = snapshot.getValue(firebase_regis::class.java)!!
                            firstname.setText(  user2.name)
                            gmail.setText(user2.gmail)
                            dob.setText(user2.dob)
                            blood.setText(  user2.bloodgroup)
                            phone.setText( user2.phone)
                            flat.setText(user2.flat)
                            area.setText( user2.area)
                            town.setText(  user2.town)
                            state.setText( user2.state)
                            emergency_name.setText(user2.emergency_name)
                            emergency_phone.setText(user2.emergency_phone)
                            emergency_flat.setText(user2.emergency_flat)
                            emergency_area.setText(user2.emergency_area)
                            emergency_town.setText(user2.emergency_town)
                            emergency_state.setText(user2.emergency_state)
                            emergency_pincode.setText(user2.emergency_pincode)


                            Toast.makeText(this@FireBase_edit_ui, user2.emergency_pincode, Toast.LENGTH_SHORT).show()

                            Glide.with(applicationContext).load(user2.linkimg).into(img)


                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(this@FireBase_edit_ui, "problem araised", Toast.LENGTH_SHORT)
                                    .show()
                        }
                    })

        }


        experience.setVisibility(View.GONE)
        review.setVisibility(View.GONE)
findViewById<ImageView>(R.id.back_firebase).setOnClickListener(View.OnClickListener {

    finish()

})

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
        auth = FirebaseAuth.getInstance()

        storageReference = FirebaseStorage.getInstance().reference
        val uid = auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("csv_data2")
        val profile = findViewById<ImageView>(R.id.profile_icon1)
        profile.setOnClickListener {
            // CHOOSEFOTO()
            chosp()
        }
        findViewById<RelativeLayout>(R.id.button_rel)?.setOnClickListener {
            if( mainImageURI == null){


                SweetAlertDialog(this@FireBase_edit_ui, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText(" Image not Uploded?")
                        .setContentText("please upload ")
                        .setCustomImage(R.drawable.danger)

                        .setConfirmText("ok")
                        .show()
            }

            else {
                progrss.visibility = View.VISIBLE

                SAVE_NAME_AND_PHOTO()
                progrss.visibility = View.VISIBLE

            }
        }

    }

    private fun CHOOSEFOTO() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            REQUEST
        )
    }
    private  fun chosp(){
        val pickPhoto = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(pickPhoto, 1)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode ==REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                if (data.data != null) {
                    mainImageURI = data.data
                    val profile =  findViewById<ImageView>(R.id.profile_icon1)

                    profile.setImageURI(mainImageURI)

                }else{
                    Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    //GETS THE URI FROM THE FOTO AND SETS THE FOTO INTO THE IMAGEVIEW
    fun uploadImage() = if(mainImageURI != null){

        val user_id = FirebaseAuth.getInstance().currentUser!!.uid

        val ref = storageReference?.child("uploads/" + user_id.toString())
        val uploadTask = ref?.putFile(mainImageURI!!)

        val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
            if (!task.isSuccessful) {
                Toast.makeText(this, "uploading", Toast.LENGTH_SHORT).show()


                task.exception?.let {
                    throw it
                }
            }
            return@Continuation ref.downloadUrl
        })
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    filePath = task.result
                    val firstname = findViewById<EditText>(R.id.user_name1).text
                    val gmail = findViewById<EditText>(R.id.user_gmail1).text
                    val dob = findViewById<EditText>(R.id.user_dob).text
                    val blood = findViewById<EditText>(R.id.user_bloodgroup).text
                    val phone =  findViewById<EditText>(R.id.user_phone1).text
                    val flat = findViewById<EditText>(R.id.flat_address).text
                    val area =  findViewById<EditText>(R.id.area_street_address).text
                    val town  = findViewById<EditText>(R.id.Town_address).text
                    val state  =  findViewById<EditText>(R.id.State_addess).text
                    val emergency_name = findViewById<EditText>(R.id.FullName_address_emergency).text
                    val emergency_phone =  findViewById<EditText>(R.id.Mobile_address_emergency).text
                    val emergency_flat = findViewById<EditText>(R.id.flat_address_emergency).text
                    val emergency_area = findViewById<EditText>(R.id.area_street_address_emergency).text
                    val emergency_town = findViewById<EditText>(R.id.Town_address_emergency).text
                    val emergency_state = findViewById<EditText>(R.id.State_addess_emergency).text
                    val emergency_pincode = findViewById<EditText>(R.id.Pincode_addess_emergency).text
                    Toast.makeText(this@FireBase_edit_ui, flat, Toast.LENGTH_SHORT).show()

                    val version = 0
                    val link = "dont know"
                    val user = firebase_regis(
                        firstname.toString(), filePath.toString(),
                        version, link,phone.toString(), gmail.toString(), dob.toString(), blood.toString(),flat.toString(),area.toString(),town.toString(),state.toString(),emergency_name.toString(),emergency_phone.toString(),emergency_flat.toString(),emergency_area.toString(),emergency_town.toString(),emergency_state.toString(),emergency_pincode.toString()
                    )

                    auth = FirebaseAuth.getInstance()
                    storageReference = FirebaseStorage.getInstance().reference
                    val uid =  auth.currentUser?.uid

                    if (uid!= null){
                        databaseReference.child(uid).setValue(user).addOnCompleteListener{
                            if(it.isSuccessful){
                                finish()
                            }
                            else{

                                Toast.makeText(
                                    this@FireBase_edit_ui,
                                    "failed bro",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                } else {
                    Toast.makeText(this@FireBase_edit_ui, "uploading IMG", Toast.LENGTH_SHORT).show()


                    // Handle failures
                }
            }?.addOnFailureListener {

            }
    }else{
        Toast.makeText(this@FireBase_edit_ui, "Please Upload an IMG", Toast.LENGTH_SHORT).show()
    }

    private fun SAVE_NAME_AND_PHOTO() {

        if( mainImageURI == null){


            SweetAlertDialog(this@FireBase_edit_ui, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(" Image not Uploded?")
                    .setContentText("please upload ")
                    .setCustomImage(R.drawable.danger)

                    .setConfirmText("ok")
                    .show()
        }


        val user_id = FirebaseAuth.getInstance().currentUser!!.uid
        val ImagesPath: StorageReference =
            storageReference?.child("profile_images")!!.child("$user_id.jpg")
        ImagesPath.putFile(mainImageURI!!).continueWithTask { task ->
            if (!task.isSuccessful) {
                throw task.exception!!
            }
            ImagesPath.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {

                filePath = task.result
                val firstname = findViewById<EditText>(R.id.user_name1).text
                val gmail = findViewById<EditText>(R.id.user_gmail1).text
                val dob = findViewById<EditText>(R.id.user_dob1).text
                val blood = findViewById<EditText>(R.id.user_bloodgroup1).text
                val version = 0
                val link = "dont know"

                val phone =  findViewById<EditText>(R.id.user_phone1).text
                val flat = findViewById<EditText>(R.id.flat_address).text
                val area =  findViewById<EditText>(R.id.area_street_address).text
                val town  = findViewById<EditText>(R.id.Town_address).text
                val state  =  findViewById<EditText>(R.id.State_addess).text
                val emergency_name = findViewById<EditText>(R.id.FullName_address_emergency).text
                val emergency_phone =  findViewById<EditText>(R.id.Mobile_address_emergency).text
                val emergency_flat = findViewById<EditText>(R.id.flat_address_emergency).text
                val emergency_area = findViewById<EditText>(R.id.area_street_address_emergency).text
                val emergency_town = findViewById<EditText>(R.id.Town_address_emergency).text
                val emergency_state = findViewById<EditText>(R.id.State_addess_emergency).text
                val emergency_pincode = findViewById<EditText>(R.id.Pincode_addess_emergency).text
                Toast.makeText(this@FireBase_edit_ui, flat, Toast.LENGTH_SHORT).show()


                val user = firebase_regis(
                        firstname.toString(), filePath.toString(),
                        version, link,phone.toString(), gmail.toString(), dob.toString(), blood.toString(),flat.toString(),area.toString(),town.toString(),state.toString(),emergency_name.toString(),emergency_phone.toString(),emergency_flat.toString(),emergency_area.toString(),emergency_town.toString(),emergency_state.toString(),emergency_pincode.toString()
                )



                auth = FirebaseAuth.getInstance()
                storageReference = FirebaseStorage.getInstance().reference
                val uid =  auth.currentUser?.uid

                if (uid!= null){
                    databaseReference.child(uid).setValue(user).addOnCompleteListener{
                        if(it.isSuccessful){
                            finish()
                        }
                        else{

                            Toast.makeText(
                                this@FireBase_edit_ui,
                                "failed bro",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            } else {
                val ERROR = task.exception!!.message
                Toast.makeText(this@FireBase_edit_ui, ERROR, Toast.LENGTH_SHORT).show()
            }
        }

    }



}
