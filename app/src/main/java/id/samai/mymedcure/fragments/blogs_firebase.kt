package id.samai.mymedcure.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.samai.mymedcure.R
import id.samai.mymedcure.activities.web_article
import id.samai.mymedcure.models.article
import kotlinx.android.synthetic.main.blog_item.view.*
import kotlinx.android.synthetic.main.fragment_blogs_firebase.*

// TODO: Rename parameter arguments, choose names that match
/*
 * A simple [Fragment] subclass.
 * Use the [blogs_firebase.newInstance] factory method to
 * create an instance of this fragment.
 */
class blogs_firebase : Fragment() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    var fragmentView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = LayoutInflater.from(activity).inflate(R.layout.fragment_blogs_firebase, container, false)
        // val view = inflater!!.inflate(R.layout.fragment_pdf, container, false)



        return fragmentView

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //(activity as AppCompatActivity).supportActionBar?.title = "Articles"

        //FirebaseApp.initializeApp(this)

        meet_progress.setVisibility(View.VISIBLE)

        mDatabase = FirebaseDatabase.getInstance().getReference("meet")
        //replace view by fragment view

        mRecyclerView = fragmentView?.findViewById(R.id.meet_view)!!
        mRecyclerView.setHasFixedSize(false)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        logRecyclerView()
    }

    private fun logRecyclerView() {
        var FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<article, UsersViewHolder>(


            article::class.java,
            R.layout.blog_item,
            UsersViewHolder::class.java,
            mDatabase


        ) {
            override fun populateViewHolder(viewHolder: UsersViewHolder, model: article, position: Int) {
                meet_progress.setVisibility(View.GONE)
                viewHolder.itemView.meet_name.text = model.name

                viewHolder.itemView.setOnClickListener {
                    val intent = Intent(context, web_article::class.java)
                    intent.putExtra("name", model.name)
                    intent.putExtra("link", model.link)
                    startActivity(intent)
                }
                // viewHolder.itemView.pdf_discription.text = model.image

                //Picasso.get().load(model.image).resize(118,113).into(viewHolder.itemView.userImageView)
                //edits for next profile


//
            }


        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter
    }

    ///view holder
    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}
