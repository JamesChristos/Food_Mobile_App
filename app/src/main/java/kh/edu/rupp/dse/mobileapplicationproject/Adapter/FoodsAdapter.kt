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
import kh.edu.rupp.dse.mobileapplicationproject.R

class FoodsAdapter(private val items: ArrayList<Foods>) : RecyclerView.Adapter<FoodsAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflate = LayoutInflater.from(context).inflate(R.layout.item_best_food, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleTxt.text = if (item.Title.length > 16) {
            "${item.Title.substring(0, 15)}..."
        } else {
            item.Title
        }
        holder.priceTxt.text = "$${item.Price}"
        holder.timeTxt.text = "${item.TimeValue} min"
        holder.rateTxt.text = item.Star.toString()

        Glide.with(context)
            .load(item.ImagePath)
            .transform(CenterCrop(), RoundedCorners(30))
            .into(holder.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", item)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView = itemView.findViewById(R.id.titleTxt)
        val priceTxt: TextView = itemView.findViewById(R.id.priceTxt)
        val timeTxt: TextView = itemView.findViewById(R.id.timeTxt)
        val rateTxt: TextView = itemView.findViewById(R.id.rateTxt)
        val pic: ImageView = itemView.findViewById(R.id.pic)
    }
}
