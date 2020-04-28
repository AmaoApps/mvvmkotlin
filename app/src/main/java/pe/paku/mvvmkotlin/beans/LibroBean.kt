package pe.paku.mvvmkotlin.beans

import CategoriaBean
import TagBean
import com.google.gson.annotations.SerializedName

data class LibroBean(

    @SerializedName("ID")
    val iD : Int,

    @SerializedName("title")
    val title : String,

    @SerializedName("author")
    val author : String,

    @SerializedName("content")
    val content : String,

    @SerializedName("content_short")
    val content_short : String,

    @SerializedName("publisher")
    val publisher : String,

    @SerializedName("publisher_date")
    val publisher_date : Int,

    @SerializedName("pages")
    val pages : Int,

    @SerializedName("language")
    val language : String,

    @SerializedName("url_details")
    val url_details : String,

    @SerializedName("url_download")
    val url_download : String,

    @SerializedName("cover")
    val cover : String,

    @SerializedName("thumbnail")
    val thumbnail : String,

    @SerializedName("num_comments")
    val num_comments : Int,

    @SerializedName("categories")
    val categories : List<CategoriaBean>,

    @SerializedName("tags")
    val tags : List<TagBean>
)
