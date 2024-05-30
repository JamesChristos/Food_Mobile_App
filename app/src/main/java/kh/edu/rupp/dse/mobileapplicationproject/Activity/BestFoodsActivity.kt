package kh.edu.rupp.dse.mobileapplicationproject.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import kh.edu.rupp.dse.mobileapplicationproject.Adapter.FoodsAdapter
import kh.edu.rupp.dse.mobileapplicationproject.Domain.Foods
import kh.edu.rupp.dse.mobileapplicationproject.R
import kh.edu.rupp.dse.mobileapplicationproject.databinding.ActivityBestFoodsBinding

class BestFoodsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBestFoodsBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBestFoodsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference

        initBestFood()
        setListeners()
    }

    private fun setListeners() {
        binding.backBtn.setOnClickListener {
            finish()  // Close the activity and go back
        }
    }

    private fun initBestFood() {
        val myRef = databaseReference.child("Foods")
        binding.progressBarFoodList.visibility = View.VISIBLE
        val list = ArrayList<Foods>()
        val query: Query = myRef.orderByChild("BestFood").equalTo(true)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(Foods::class.java)!!)
                    }
                    if (list.isNotEmpty()) {
                        binding.foodListView.layoutManager = GridLayoutManager(this@BestFoodsActivity, 2)
                        val adapter = FoodsAdapter(list)
                        binding.foodListView.adapter = adapter
                    }
                    binding.progressBarFoodList.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled
                binding.progressBarFoodList.visibility = View.GONE
            }
        })
    }
}
