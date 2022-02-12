abstract class Persistance {

    companion object{
        fun createDBPersistence() : DBPersistence{
            return DBPersistence()
        }
    }

    //set all data to DBPersistence Kotlin class for make timetable
    abstract fun AddTimeTableSave(levelName: String, courseName: String,
                                  moduleName: String, roomName: String, setectTime: String)

    //get all data from DBPersistence Kotlin class for listing all data on dropdown list
    abstract fun getAllCourse(): Array<String?>
    abstract fun getAllLevel(): Array<String?>
    abstract fun getAllModule(): Array<String?>
    abstract fun getAllRoom(): Array<String?>
    abstract fun getAllTime(): Array<String?>

    //get boolean from DBPersistence Kotlin class to check if any clash happen or not
//    abstract fun getClashKotlin(): Boolean

    //set all data to DBPersistence Kotlin class to add different level,course and module
    abstract fun AddLevel(addLevel: String)
    abstract fun AddCourse(addLevel: String)
    abstract fun AddModule(addModule: String)
}