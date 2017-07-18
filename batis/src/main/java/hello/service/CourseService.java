package hello.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hello.BatisUtil;
import hello.domain.Course;
import hello.mappers.CourseMapper;

public class CourseService {
  private Logger logger = LoggerFactory.getLogger(getClass());
  
  public List<Course> searchCourses(Map<String, Object> map) {
    logger.debug("searchCourses By :" + map);
    SqlSession sqlSession = BatisUtil.getSqlSessionFactoryUsingXML().openSession();
    try {
      CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
      return mapper.searchCourses(map);
    } finally {
      sqlSession.close();
    }
  }

  public List<Course> searchCoursesByTutors(Map<String, Object> map) {
    SqlSession sqlSession = BatisUtil.getSqlSessionFactoryUsingXML().openSession();
    try {
      CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
      return mapper.searchCoursesByTutors(map);
    } finally {
      sqlSession.close();
    }
  }
}
