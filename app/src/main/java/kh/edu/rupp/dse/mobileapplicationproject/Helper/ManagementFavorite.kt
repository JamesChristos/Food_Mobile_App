package kh.edu.rupp.dse.mobileapplicationproject.Helperimport

import android.content.Context
import android.widget.Toast
import kh.edu.rupp.dse.mobileapplicationproject.Domain.Foods
import kh.edu.rupp.dse.mobileapplicationproject.Helper.TinyDB

class ManagementFavorite(private val context: Context) {
    private val tinyDB: TinyDB = TinyDB(context)

    fun insertFavorite(item: Foods) {
        val listFavorites: ArrayList<Foods> = getListFavorites()
        var existAlready = false
        for (food in listFavorites) {
            if (food.Title == item.Title) {
                existAlready = true
                break
            }
        }
        if (!existAlready) {
            listFavorites.add(item)
            tinyDB.putListObject("FavoriteList", listFavorites)
            Toast.makeText(context, "Added to your Favorites", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Already in your Favorites", Toast.LENGTH_SHORT).show()
        }
    }

    fun getListFavorites(): ArrayList<Foods> {
        return tinyDB.getListObject("FavoriteList") as ArrayList<Foods>
    }

    fun removeFavorite(item: Foods) {
        val listFavorites: ArrayList<Foods> = getListFavorites()
        val iterator = listFavorites.iterator()
        while (iterator.hasNext()) {
            val food = iterator.next()
            if (food.Title == item.Title) {
                iterator.remove()
                break
            }
        }
        tinyDB.putListObject("FavoriteList", listFavorites)
        Toast.makeText(context, "Removed from your Favorites", Toast.LENGTH_SHORT).show()
    }
}
