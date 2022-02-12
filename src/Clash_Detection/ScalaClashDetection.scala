package Clash_Detection

import java.sql.DriverManager
import java.sql.Connection

object ScalaClashDetection {
  def main(): Boolean = {
    // connect to the database named "mysql" on the localhost
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/time_table_scheduling"
    val username = "root"
    val password = ""

    var temp = true

    // there's probably a better way to do this
    var connection:Connection = null
    var slots : List[TimeSlot] = List()
    case class TimeSlot(course: String, module: String, room: String, time: String)
    try {
      // make the connection
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)

      // create the statement, and run the select query
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM time_table")
      while ( resultSet.next() ) {
        val course = resultSet.getString("course")
        val module = resultSet.getString("module")
        val room = resultSet.getString("room")
        val time = resultSet.getString("time")

        slots = List.concat(slots, List(TimeSlot(course, module, room, time)))
      }
      connection.close()
      for (i <- 0 to slots.length-1 ) {
        var slot_i = slots(i);
        for (j <- i+1 to slots.length-1 ) {
          var slot_j = slots(j)

          if (slot_i.time == slot_j.time && slot_i.room == slot_j.room && slot_i.module != slot_j.module) {
            println("Scala")
            println(slot_i.time)
            println(slot_j.time)
            println(slot_i.room)
            println(slot_j.room)
            println(slot_i.module)
            println(slot_j.module)
            temp = true
            return temp
          }
        }
      }
      temp = false
      return temp;
    }
  }
}
