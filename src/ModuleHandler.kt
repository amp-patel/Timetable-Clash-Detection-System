import DB.DBConnect
import com.mysql.jdbc.ResultSet
import java.sql.PreparedStatement

class ModuleHandler {

    fun createModule(): Module
    {
        val module = Module();

        return module;
    }

    fun saveModule(module: Module): Array<String?>
    {
        //data persistence
        val persistance: Persistance

        persistance=Persistance.createDBPersistence()
        return persistance.getAllModule()
    }
}