package model

import java.io.File

object Util {
    fun fixAndRename(file: File, newName: String): Boolean =
        file.renameTo(File(fixString(newName)))


    fun fixString(newName: String): String {
        return newName.replace(Regex("<|>|:|\"|\\|\\||\\?"), ".")
    }
}