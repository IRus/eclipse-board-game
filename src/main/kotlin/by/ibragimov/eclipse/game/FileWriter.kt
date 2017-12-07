package by.ibragimov.eclipse.game

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

interface FileWriter {
    fun write(directory: String, filename: String, text: String)
}

class DefaultFileWriter : FileWriter {
    override fun write(directory: String, filename: String, text: String) {
        Files.createDirectories(Paths.get(directory))
        Files.write(
            Paths.get(directory, filename),
            text.toByteArray(),
            StandardOpenOption.CREATE,
            StandardOpenOption.WRITE,
            StandardOpenOption.TRUNCATE_EXISTING
        )
    }
}