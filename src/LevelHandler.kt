class LevelHandler {

    fun createLevel(): Level
    {
        val level = Level();

        return level;
    }

    fun saveLevel(level: Level): Array<String?>
    {
        //data persistence
        val persistance: Persistance

        persistance=Persistance.createDBPersistence()
        return persistance.getAllLevel()

    }
}