package id.samai.mymedcure.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import id.samai.mymedcure.R
import id.samai.mymedcure.models.firebase_regis
import java.util.*

class FireBase_registration : AppCompatActivity() {

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
        setContentView(R.layout.activity_fire_base_registration)
        auth = FirebaseAuth.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        val uid = auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("csv_data2")
        val profile = findViewById<ImageView>(R.id.user_profile_icon)
        profile.setOnClickListener {
           // CHOOSEFOTO()
            chosp()
        }
        findViewById<Button>(R.id.button_regis)?.setOnClickListener {


SAVE_NAME_AND_PHOTO()

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
                    val profile =  findViewById<ImageView>(R.id.user_profile_icon)

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
                    val firstname = findViewById<EditText>(R.id.user_name).text
                    val gmail = findViewById<EditText>(R.id.user_gmail).text
                    val dob = findViewById<EditText>(R.id.user_dob).text
                    val blood = findViewById<EditText>(R.id.user_bloodgroup).text
                    val version = 0
                    val link = "dont know"
                    val user = firebase_regis(
                        firstname.toString(), filePath.toString(),
                        version, link, gmail.toString(), dob.toString(), blood.toString()
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
                                    this@FireBase_registration,
                                    "failed bro",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                } else {
                    Toast.makeText(this, "uploading IMG", Toast.LENGTH_SHORT).show()


                    // Handle failures
                }
            }?.addOnFailureListener {

            }
    }else{
        Toast.makeText(this, "Please Upload an IMG", Toast.LENGTH_SHORT).show()
    }

    private fun SAVE_NAME_AND_PHOTO() {
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
                    val firstname = findViewById<EditText>(R.id.user_name).text
                    val gmail = findViewById<EditText>(R.id.user_gmail).text
                    val dob = findViewById<EditText>(R.id.user_dob).text
                    val blood = findViewById<EditText>(R.id.user_bloodgroup).text
                    val version = 0
                    val link = "dont know"
                    val user = firebase_regis(
                        firstname.toString(), filePath.toString(),
                        version, link, gmail.toString(), dob.toString(), blood.toString()
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
                                    this@FireBase_registration,
                                    "failed bro",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                } else {
                    val ERROR = task.exception!!.message
                    Toast.makeText(this@FireBase_registration, ERROR, Toast.LENGTH_SHORT).show()
                }
            }

    }


}
