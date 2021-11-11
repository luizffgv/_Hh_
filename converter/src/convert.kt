import java.io.File
import java.io.FileNotFoundException

//*******************************************************************************************************
//*                                         *convert.kt*                                                *
//*     This converts the cpp code to the (rightfully better) H code. I wrote this in kotlin cuz        *
//*     strings in c are awful :(                                                                       *
//*                                                                                                     *
//*             Args:                                                                                   *
//*                   1) path of file to be converted                                                   *
//*                   2) [optional] the name of the output file. (defaults to the name of the file)     *
//*******************************************************************************************************


fun main(args:Array<String>){
    val dictionary : HashMap<String,String> = java.util.HashMap()

    cacheDictionary(dictionary)

    try {
        val filePath = args[0]
        val file     = File(filePath)
        val outputFileName = if(args.size==2) args[1] else file.name

        val fileOut = File(outputFileName)

        file.forEachLine {
            val textTransformed = convertToH(it)
            fileOut.writeText(textTransformed)
        }

    }catch (h:ArrayIndexOutOfBoundsException){
        println("""
            ERR: You need to supply:
                (1) The file path (local or global) to convert the file.
                (2) [OPTIONAL] The output file name. (defaults to the file name)""".trimIndent())
    }catch (h:FileNotFoundException){
        println("""
            ERR: The file path must be a valid path!!
        """.trimIndent())
    }
}

fun cacheDictionary(dictionary: HashMap<String, String>) {
    val hFile = File("h.h")
    hFile.forEachLine {
        if(it.startsWith("//") || it.isBlank()) return@forEachLine
        else{
            if(it.startsWith("#define")) {
                val arr = it.split(Regex("[ ]+"))
                dictionary[arr[2]] = arr[1]
            }
        }
    }
}

fun convertToH(h: String): String {
    TODO("Not yet implemented")
}
