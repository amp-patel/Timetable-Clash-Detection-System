package Clash_Detection

import DB.DBConnect
import com.mysql.jdbc.ResultSet
import java.sql.PreparedStatement

class ClashDetection {
    fun ClashCheck(): Boolean
    {
        try {

            var a = 1
            var st: PreparedStatement
            var sql = "select * from time_table"
            st = DBConnect.con!!.prepareStatement(sql)
            DBConnect.rs = st.executeQuery() as ResultSet?
            while (DBConnect.rs?.next() == true) {
                a = a + 1
            }
            sql = "select * from time_table ORDER BY time DESC"
            st = DBConnect.con!!.prepareStatement(sql)
            DBConnect.rs = st.executeQuery() as ResultSet?
            val classes = arrayOfNulls<String>(a)
            var slots = mutableListOf<ClashKotlin.TimeSlot>()

            while (DBConnect.rs?.next() == true) {
                a--
                var slot = ClashKotlin.TimeSlot(
                    DBConnect.rs!!.getString("course"),
                    DBConnect.rs!!.getString("module"),
                    DBConnect.rs!!.getString("room"),
                    DBConnect.rs!!.getString("time")
                )
                slots.add(slot);

                val class_name: String = DBConnect.rs!!.getString("time")
                classes[a] = class_name
//                println(classes[a])
            }
            println(slots)
            for (i in 0..slots.size-1 ) {
                var slot_i = slots.elementAt(i);
                for (j in i+1..slots.size-1 ) {
                    var slot_j = slots.elementAt(j)

                    if (slot_i.time == slot_j.time && slot_i.room == slot_j.room && slot_i.module != slot_j.module) {

                        return true;
                    }
                }
            }
            return false;
        }
        catch (e: Exception)
        {
            return false
        }
    }
}