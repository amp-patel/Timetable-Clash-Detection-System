class AddLevelHandlerKt {

    fun createAddLevel(addLevelName: String): AddLevelKt
    {
        val addL = AddLevelKt(addLevelName=addLevelName);

        return addL;
    }

    fun saveAddLevel(addL: AddLevelKt)
    {
        //data persistence
        val persistance: Persistance

        persistance=Persistance.createDBPersistence()
        persistance.AddLevel(addL.addLevelName.toString())

    }
}