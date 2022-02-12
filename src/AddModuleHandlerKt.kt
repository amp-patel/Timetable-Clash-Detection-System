class AddModuleHandlerKt {
    fun createAddModule(addModuleName: String): AddModuleKt
    {
        val addM = AddModuleKt(addModuleName=addModuleName);

        return addM;
    }

    fun saveAddModule(addM: AddModuleKt)
    {
        //data persistence
        val persistance: Persistance

        persistance=Persistance.createDBPersistence()
        persistance.AddModule(addM.addModuleName.toString())

    }
}