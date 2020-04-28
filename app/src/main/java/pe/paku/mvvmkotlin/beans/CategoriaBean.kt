import com.google.gson.annotations.SerializedName

data class CategoriaBean (

	@SerializedName("category_id")
	val category_id : Int,

	@SerializedName("name")
	val name : String,

	@SerializedName("nicename")
	val nicename : String

)