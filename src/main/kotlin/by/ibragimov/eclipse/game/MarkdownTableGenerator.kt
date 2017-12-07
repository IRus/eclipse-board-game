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
        val header = table.rows.filterIsInstance<Header>().firstOrNull()
        val rows = table.rows.filterIsInstance<Row>()

        val sb = StringBuilder().also { builder ->
            header?.let { row ->
                builder.append(row.columns.toRow())
                builder.append(row.columns.map { "---" }.toRow())
            }

            rows.forEach { row ->
                builder.append(row.columns.toRow())
            }
        }

        return sb.toString()
    }

    private fun List<String>.toRow(): String {
        return this.joinToString(separator = " | ", prefix = "| ", postfix = " | \n")
    }
}