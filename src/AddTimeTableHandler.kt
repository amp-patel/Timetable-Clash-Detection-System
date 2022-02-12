class AddTimeTableHandler {

    fun createAddTimeTable(levelName: String, courseName: String,
                           moduleName: String, roomName: String, setectTime: String,): AddTimeTable
    {
        val temp = AddTimeTable(levelName=levelName,courseName=courseName,
            moduleName=moduleName,roomName=roomName,setectTime=setectTime);

        return temp;
    }

    fun saveAddTimeTable(temp: AddTimeTable)
    {
        //data persistence
        val persistance: Persistance

            persistance=Persistance.createDBPersistence()
        persistance.AddTimeTableSave(temp.levelName.toString(),temp.courseName.toString(),
            temp.moduleName.toString(),temp.roomName.toString(),temp.setectTime.toString())

    }
}