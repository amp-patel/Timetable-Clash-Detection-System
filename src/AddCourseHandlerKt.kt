class AddCourseHandlerKt {
    fun createAddCourse(addCourseName: String): AddCourseKt
    {
        val addC = AddCourseKt(addCourseName=addCourseName);

        return addC;
    }

    fun saveAddCourse(addL: AddCourseKt)
    {
        //data persistence
        val persistance: Persistance

        persistance=Persistance.createDBPersistence()
        persistance.AddCourse(addL.addCourseName.toString())

    }
}