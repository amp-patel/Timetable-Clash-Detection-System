class CourseHandler {

    fun createCourse(): Course
    {
        val course = Course();

        return course;
    }

    fun saveCourse(course: Course): Array<String?>
    {
        //data persistence
        val persistance: Persistance

        persistance=Persistance.createDBPersistence()
        return persistance.getAllCourse()

    }
}