package by.ibragimov.eclipse.game

data class Table(
    val rows: List<TableRow>
)

sealed class TableRow
class Header(val columns: List<String>) : TableRow()
class Row(val columns: List<String>) : TableRow()

interface MarkdownTableGenerator {
    fun generate(table: Table): String
}

class DefaultMarkdownTableGenerator : MarkdownTableGenerator {
    override fun generate(table: Table): String {
        validate(table)

        val header = table.rows.filterIsInstance<Header>().firstOrNull()
        val rows = table.rows.filterIsInstance<Row>()

        val sb = StringBuilder().also { builder ->
            header?.let { row ->
                builder.append(row.columns.joinToString(separator = " | ", prefix = "| ", postfix = " |")).append("\n")
                builder.append(row.columns.map { "---" }.joinToString(separator = " | ", prefix = "| ", postfix = " |")).append("\n")
            }

            rows.forEach { row ->
                builder.append(row.columns.joinToString(separator = " | ", prefix = "| ", postfix = " |")).append("\n")
            }

        }

        return sb.toString()
    }

    private fun validate(table: Table) {
        println(table)
    }
}