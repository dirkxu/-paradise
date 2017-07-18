package hello.mappers;

import hello.domain.Tutor;

public interface TutorMapper {
  Tutor selectTutorWithCourses(int tutorId);

  Tutor selectTutorById(int tutorId);
}
