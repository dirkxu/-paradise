package hello;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import hello.domain.Course;
import hello.service.CourseService;

public class CourseServiceTest {

  private static CourseService courseService;

  @BeforeClass
  public static void setup() {
    courseService = new CourseService();
  }

  @AfterClass
  public static void teardown() {
    courseService = null;
  }
  
  @Test
  public void searchCourses() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("tutorId", 1);
    // map.put("courseName", "%java%");
    map.put("endDate", new Date());
    List<Course> courses = courseService.searchCourses(map);
    assertNotNull(courses);
    for (Course course : courses) {
      assertNotNull(course);
      System.out.println(course);
    }
  }

  @Test
  public void searchCoursesByTutors() {
    Map<String, Object> map = new HashMap<String, Object>();
    List<Integer> tutorIds = new ArrayList<Integer>();
    tutorIds.add(1);
    tutorIds.add(2);
    map.put("tutorIds", tutorIds);
    map.put("courseName", "%java%");
    map.put("startDate", new Date());
    List<Course> courses = courseService.searchCoursesByTutors(map);
    assertNotNull(courses);
    for (Course course : courses) {
      assertNotNull(course);
      System.out.println(course);
    }
  }
}
