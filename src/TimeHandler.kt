class TimeHandler {

    fun createTime(): Time
    {
        val time = Time();

        return time;
    }

    fun saveTime(time: Time): Array<String?>
    {
        //data persistence
        val persistance: Persistance

        persistance=Persistance.createDBPersistence()
        return persistance.getAllTime()
    }
}