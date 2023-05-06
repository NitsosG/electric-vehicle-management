package aueb.msc.model

data class Model(val code: String , val name: String, val brandCode: String){
    override fun toString(): String {
        return code;
    }
}
