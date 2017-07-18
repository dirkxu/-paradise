package hello;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import hello.domain.Course;
import hello.domain.Tutor;
import hello.service.TutorService;

public class TutorServiceTest {

  private static TutorService tutorService;

  @BeforeClass
  public static void setup() {
    tutorService = new TutorService();
  }

  @AfterClass
  public static void teardown() {
    tutorService = null;
  }

  @Test
  public void testFindTutorById() {
    Tutor tutor = tutorService.findTutorById(1);
    assertNotNull(tutor);
    List<Course> courses = tutor.getCourses();
    assertNotNull(courses);
    for (Course course : courses) {
      assertNotNull(course);
      System.out.println(course);
    }
  }
}
