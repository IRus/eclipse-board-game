package by.ibragimov.eclipse.game

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Running generation")

        Files.createDirectories(Paths.get("public"))
        Files.write(
                Paths.get("./public/index.md"),
                "# Hello".toByteArray(),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING
        )
    }
}