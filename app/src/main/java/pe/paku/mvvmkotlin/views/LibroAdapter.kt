package pe.paku.mvvmkotlin.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pe.paku.mvvmkotlin.R
import pe.paku.mvvmkotlin.beans.LibroBean

class LibroAdapter(
    private var libros : List<LibroBean>,
    private val context : Context
) : RecyclerView.Adapter<LibroAdapter.LibrosViewHolder>() {

    class LibrosViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val titulo : TextView = view.findViewById(R.id.txt_libro_titulo)
        val author : TextView = view.findViewById(R.id.txt_libro_autor)
        val imagen : ImageView = view.findViewById(R.id.img_libro)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrosViewHolder {

        val ViewLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_libro, parent, false)
        return LibrosViewHolder(ViewLayout)

    }

    override fun getItemCount(): Int {
        return libros.size
    }

    override fun onBindViewHolder(holder: LibrosViewHolder, position: Int) {
        val libroData = libros.get(position)
        holder.titulo.text = libroData.title
        holder.author.text = libroData.author

        //Imagen android
        val urlImage = libroData.thumbnail

        Picasso.get().load(urlImage).into(holder.imagen)


        /*Usando Glide
        Glide.with(context)
            .load(urlImage)
            .centerCrop()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>, p3: Boolean): Boolean {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
                override fun onResourceReady(p0: Drawable?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>, p3: DataSource?, p4: Boolean): Boolean {
                    Log.d("GLIDE", "OnResourceReady")
                    //do something when picture already loaded
                    return false
                }
            })
            .error(R.drawable.error_image)
            .fallback(R.drawable.error_image)
            .into(holder.imagen) */


    }

    fun updateLibros(newData: List<LibroBean>){
        libros = newData
        notifyDataSetChanged()
    }

}