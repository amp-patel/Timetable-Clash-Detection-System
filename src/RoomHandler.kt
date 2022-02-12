class RoomHandler {

    fun createRoom(): Room
    {
        val room = Room()

        return room
    }

    fun saveRoom(room: Room): Array<String?>
    {
        //data persistence
        val persistance: Persistance

        persistance=Persistance.createDBPersistence()
        return persistance.getAllRoom()

    }
}