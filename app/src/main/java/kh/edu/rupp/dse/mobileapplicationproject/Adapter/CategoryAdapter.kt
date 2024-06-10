package kh.edu.rupp.dse.mobileapplicationproject.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kh.edu.rupp.dse.mobileapplicationproject.Activity.ListFoodActivity
import kh.edu.rupp.dse.mobileapplicationproject.Domain.Category
import kh.edu.rupp.dse.mobileapplicationproject.R

class CategoryAdapter(private val items: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_category, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleTxt.text = item.Name

//        // Load the image using Glide
        if (position == 0) {
            // Use pizza.jpg for the first item
            Glide.with(context)
                .load(R.drawable.pizza) // Assuming pizza.jpg is in the drawable folder
                .into(holder.pic)
        }
        else if (position == 1) {
            // Use burger.jpg for the second item
            Glide.with(context)
                .load(R.drawable.burger) // Assuming burger.jpg is in the drawable folder
                .into(holder.pic)
        }
        else if (position == 2) {
            Glide.with(context)
                .load(R.drawable.chicken) // Assuming burger.jpg is in the drawable folder
                .into(holder.pic)
        }
        else if (position == 3) {
            // Use salad.jpg for the fourth item
            Glide.with(context)
                .load(R.drawable.soup) // Assuming salad.jpg is in the drawable folder
                .into(holder.pic)
        }
        else if (position == 4) {
            // Use dessert.jpg for the fifth item
            Glide.with(context)
                .load(R.drawable.meat) // Assuming dessert.jpg is in the drawable folder
                .into(holder.pic)
        }
        else if (position == 5) {
            // Use drink.jpg for the sixth item
            Glide.with(context)
                .load(R.drawable.bakery) // Assuming drink.jpg is in the drawable folder
                .into(holder.pic)
        }
        else if (position == 6) {
            // Use other.jpg for the seventh item
            Glide.with(context)
                .load(R.drawable.drink) // Assuming other.jpg is in the drawable folder
                .into(holder.pic)
        }
        else if (position == 7) {
            // Use other.jpg for the seventh item
            Glide.with(context)
                .load(R.drawable.fish) // Assuming other.jpg is in the drawable folder
                .into(holder.pic)
        }
        else {
            // Load the image based on the item's ImagePath
            val drawableResourceId = context.resources.getIdentifier(item.ImagePath, "drawable", context.packageName)
            Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ListFoodActivity::class.java)
            intent.putExtra("CategoryId", items[position].Id)
            intent.putExtra("CategoryName", items[position].Name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.catNameTxt)
        val pic: ImageView = itemView.findViewById(R.id.imgCat)
    }
}
