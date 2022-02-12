import DB.DBConnect
import com.mysql.jdbc.ResultSet
import java.sql.PreparedStatement

class DBPersistence(): Persistance() {

    init
    {
        //db connection
        DBConnect.connect()
    }

    //get all data from Persistence Kotlin class to insert all data to database
    override fun AddTimeTableSave(levelName: String, courseName: String,
                                  moduleName: String, roomName: String, setectTime: String) {

        try {
            val st: PreparedStatement
            val sql1 = "insert into time_table (level, course, module, room, time) values (?, ?, ?, ?, ?)"
            st = DBConnect.con?.prepareStatement(sql1)!!
            st.setString(1, levelName.trim())
            println(courseName.trim())
            st.setString(2, courseName.trim())
            println(courseName.trim())
            st.setString(3, moduleName.trim())
            println(moduleName.trim())
            st.setString(4, roomName.trim())
            println(roomName.trim())
            st.setString(5, setectTime.trim())
            println(setectTime.trim())
            st.executeUpdate()
        }
        catch (ex: Exception)
        {
            println(ex)
        }
    }

    //get all data from Persistence Kotlin class to insert new level to database
    override fun AddLevel(addLevel: String) {
        try {
            val st: PreparedStatement
            val sql1 = "insert into level_study (level_name) values (?)"
            st = DBConnect.con?.prepareStatement(sql1)!!
            st.setString(1, addLevel.trim())

            st.executeUpdate()
        }
        catch (ex: Exception)
        {
            println(ex)
        }
    }

    //get all data from Persistence Kotlin class to insert new course to database
    override fun AddCourse(addCourse: String) {
        try {
            val st: PreparedStatement
            val sql1 = "insert into class (class_name) values (?)"
            st = DBConnect.con?.prepareStatement(sql1)!!
            st.setString(1, addCourse.trim())

            st.executeUpdate()
        }
        catch (ex: Exception)
        {
            println(ex)
        }
    }

    //get all data from Persistence Kotlin class to insert new module to database
    override fun AddModule(addModule: String) {
        try {
            val st: PreparedStatement
            val sql1 = "insert into course (course_name) values (?)"
            st = DBConnect.con?.prepareStatement(sql1)!!
            st.setString(1, addModule.trim())

            st.executeUpdate()
        }
        catch (ex: Exception)
        {
            println(ex)
        }
    }

    //set all data from database to Persistence Kotlin class for showing all data to course dropdown list
    override fun getAllCourse(): Array<String?> {
        try {

            var a = 1
            var st: PreparedStatement
            var sql = "select class_name from class"
            st = DBConnect.con!!.prepareStatement(sql)
            DBConnect.rs = st.executeQuery() as ResultSet?
            while (DBConnect.rs?.next() == true) {
                a = a + 1
            }
            sql = "select class_name from class ORDER BY class_name DESC"
            st = DBConnect.con!!.prepareStatement(sql)
            DBConnect.rs = st.executeQuery() as ResultSet?
            val classes = arrayOfNulls<String>(a)
            while (DBConnect.rs?.next() == true) {
                a--
                val class_name: String = DBConnect.rs!!.getString("class_name")
                classes[a] = class_name
//                println(classes[a])
            }
            classes[0] = "-- Select Course --"


            return classes
        } catch (e: Exception) {
            val a = arrayOfNulls<String>(0);
            return a
            ;
        }
    }

    //set all data from database to Persistence Kotlin class for showing all data to level dropdown list
    override fun getAllLevel(): Array<String?> {
        try {

            var a = 1
            var st: PreparedStatement
            var sql = "select level_name from level_study"
            st = DBConnect.con!!.prepareStatement(sql)
            DBConnect.rs = st.executeQuery() as ResultSet?
            while (DBConnect.rs?.next() == true) {
                a = a + 1
            }
            sql = "select level_name from level_study ORDER BY level_name DESC"
            st = DBConnect.con!!.prepareStatement(sql)
            DBConnect.rs = st.executeQuery() as ResultSet?
            val classes = arrayOfNulls<String>(a)
            while (DBConnect.rs?.next() == true) {
                a--
                val class_name: String = DBConnect.rs!!.getString("level_name")
                classes[a] = class_name
//                println(classes[a])
            }
            classes[0] = "-- Select Level of Study --"


            return classes
        } catch (e: Exception) {
            val a = arrayOfNulls<String>(0);
            return a
            ;
        }
    }

    //set all data from database to Persistence Kotlin class for showing all data to module dropdown list
    override fun getAllModule(): Array<String?> {
        try {

            var a = 1
            var st: PreparedStatement
            var sql = "select course_name from course"
            st = DBConnect.con!!.prepareStatement(sql)
            DBConnect.rs = st.executeQuery() as ResultSet?
            while (DBConnect.rs?.next() == true) {
                a = a + 1
            }
            sql = "select course_name from course ORDER BY course_name DESC"
            st = DBConnect.con!!.prepareStatement(sql)
            DBConnect.rs = st.executeQuery() as ResultSet?
            val classes = arrayOfNulls<String>(a)
            while (DBConnect.rs?.next() == true) {
                a--
                val class_name: String = DBConnect.rs!!.getString("course_name")
                classes[a] = class_name
//                println(classes[a])
            }
            classes[0] = "-- Select Module --"
            return classes
        } catch (e: Exception) {
            val a = arrayOfNulls<String>(0);
            return a
            ;
        }
    }

    //set all data from database to Persistence Kotlin class for showing all data to room dropdown list
    override fun getAllRoom(): Array<String?> {
        try {

            var a = 1
            var st: PreparedStatement
            var sql = "select class_room_name from class_room"
            st = DBConnect.con!!.prepareStatement(sql)
            DBConnect.rs = st.executeQuery() as ResultSet?
            while (DBConnect.rs?.next() == true) {
                a = a + 1
            }
            sql = "select class_room_name from class_room ORDER BY class_room_name DESC"
            st = DBConnect.con!!.prepareStatement(sql)
            DBConnect.rs = st.executeQuery() as ResultSet?
            val classes = arrayOfNulls<String>(a)
            while (DBConnect.rs?.next() == true) {
                a--
                val class_name: String = DBConnect.rs!!.getString("class_room_name")
                classes[a] = class_name
//                println(classes[a])
            }
            classes[0] = "-- Select Room  --"


            return classes
        } catch (e: Exception) {
            val a = arrayOfNulls<String>(0);
            return a
            ;
        }
    }

    //set all data from database to Persistence Kotlin class for showing all data to time dropdown list
    override fun getAllTime(): Array<String?> {
        try {

            var a = 1
            var st: PreparedStatement
            var sql = "select time_title from meeting_time"
            st = DBConnect.con!!.prepareStatement(sql)
            DBConnect.rs = st.executeQuery() as ResultSet?
            while (DBConnect.rs?.next() == true) {
                a = a + 1
            }
            sql = "select time_title from meeting_time ORDER BY time_title DESC"
            st = DBConnect.con!!.prepareStatement(sql)
            DBConnect.rs = st.executeQuery() as ResultSet?
            val classes = arrayOfNulls<String>(a)
            while (DBConnect.rs?.next() == true) {
                a--
                val class_name: String = DBConnect.rs!!.getString("time_title")
                classes[a] = class_name
//                println(classes[a])
            }
            classes[0] = "-- Select Time --"


            return classes
        } catch (e: Exception) {
            val a = arrayOfNulls<String>(0);
            return a
            ;
        }
    }
}