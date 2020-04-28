import com.google.gson.annotations.SerializedName

data class TagBean (

	@SerializedName("tag_id")
	val tag_id : Int,

	@SerializedName("name")
	val name : String,

	@SerializedName("nicename")
	val nicename : String

)