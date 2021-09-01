package model

object Util {
    fun fixString(newName: String): String {
        return newName.replace(Regex("<|>|:|\"|\\|\\||\\?"), ".")
    }
}