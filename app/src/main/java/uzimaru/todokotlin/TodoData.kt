package uzimaru.todokotlin

import java.util.*

data class TodoData
    (val id: Int,
     val title: String,
     val deadLine: Date,
     val complete: Boolean)