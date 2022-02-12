package DB

import com.mysql.jdbc.Connection
import com.mysql.jdbc.Driver
import com.mysql.jdbc.ResultSet
import com.mysql.jdbc.Statement
import java.sql.DriverManager
import java.sql.SQLException
import javax.swing.JOptionPane

object DBConnect {
    var con: Connection? = null
    var rs: ResultSet? = null
    var st: Statement? = null
    fun connect() {
        try {
            DriverManager.registerDriver(Driver())
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/time_table_scheduling", "root", "") as Connection?
            st = con?.createStatement() as Statement?
        } catch (e: SQLException) {
            JOptionPane.showMessageDialog(null, "Cannot connect to the database", "Issue!", JOptionPane.OK_OPTION)
        }
    }
}