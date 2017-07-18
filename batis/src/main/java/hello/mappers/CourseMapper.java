package hello.mappers;

import java.util.List;
import java.util.Map;

import hello.domain.Course;

public interface CourseMapper {

  List<Course> searchCourses(Map<String, Object> map);

  List<Course> searchCoursesByTutors(Map<String, Object> map);

}
