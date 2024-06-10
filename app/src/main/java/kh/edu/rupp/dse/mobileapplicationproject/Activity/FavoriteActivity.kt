package kh.edu.rupp.dse.mobileapplicationproject.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.dse.mobileapplicationproject.Adapter.FavoriteAdapter
import kh.edu.rupp.dse.mobileapplicationproject.Helperimport.ManagementFavorite
import kh.edu.rupp.dse.mobileapplicationproject.databinding.ActivityFavoriteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var managementFavorite: ManagementFavorite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementFavorite = ManagementFavorite(this)

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

        initList()
    }

    private fun initList() {
        // Show progress bar before loading data
        binding.progressBarFavoriteList.visibility = View.VISIBLE

        // Simulate asynchronous data fetching
        GlobalScope.launch(Dispatchers.Main) {
            val favoriteList = withContext(Dispatchers.IO) {
                // Simulate long-running operation
                managementFavorite.getListFavorites()
            }

            if (favoriteList.isEmpty()) {
                binding.emptyTxt.visibility = View.VISIBLE
                binding.favoriteListView.visibility = View.GONE
            } else {
                binding.emptyTxt.visibility = View.GONE
                binding.favoriteListView.visibility = View.VISIBLE
            }

            val gridLayoutManager = GridLayoutManager(this@FavoriteActivity, 2)
            binding.favoriteListView.layoutManager = gridLayoutManager
            adapter = FavoriteAdapter(favoriteList, this@FavoriteActivity)
            binding.favoriteListView.adapter = adapter

            // Hide progress bar after loading data
            binding.progressBarFavoriteList.visibility = View.GONE
        }
    }
}
