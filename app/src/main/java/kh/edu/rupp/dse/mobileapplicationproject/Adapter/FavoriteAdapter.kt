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
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kh.edu.rupp.dse.mobileapplicationproject.Activity.DetailActivity
import kh.edu.rupp.dse.mobileapplicationproject.Domain.Foods
import kh.edu.rupp.dse.mobileapplicationproject.Helperimport.ManagementFavorite
import kh.edu.rupp.dse.mobileapplicationproject.R

class FavoriteAdapter(
    private val favoriteList: ArrayList<Foods>,
    private var context: Context
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val managementFavorite: ManagementFavorite = ManagementFavorite(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_favorite, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = favoriteList[position]
        holder.titleTxt.text = if (food.Title.length > 16) {
            "${food.Title.substring(0, 15)}..."
        } else {
            food.Title
        }
        holder.priceTxt.text = "$${food.Price}"
        holder.timeTxt.text = "${food.TimeValue} mins"
        holder.starTxt.text = food.Star.toString()

        // Load image using Glide
        Glide.with(context)
            .load(food.ImagePath)
            .transform(CenterCrop(), RoundedCorners(30))
            .into(holder.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", food)
            context.startActivity(intent)
        }

        holder.btnRemove.setOnClickListener {
            managementFavorite.removeFavorite(food)
            favoriteList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, favoriteList.size)
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.titleTxt)
        val priceTxt: TextView = itemView.findViewById(R.id.priceTxt)
        val timeTxt: TextView = itemView.findViewById(R.id.timeTxt)
        val starTxt: TextView = itemView.findViewById(R.id.rateTxt)
        val pic: ImageView = itemView.findViewById(R.id.pic)
        val btnRemove: ImageView = itemView.findViewById(R.id.btnRemove)
    }
}
