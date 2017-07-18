package hello.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hello.BatisUtil;
import hello.domain.Tutor;
import hello.mappers.TutorMapper;

public class TutorService {
  private Logger logger = LoggerFactory.getLogger(getClass());


  public Tutor findTutorById(int tutorId) {
    logger.debug("findTutorById :" + tutorId);
    SqlSession sqlSession = BatisUtil.getSqlSessionFactoryUsingXML().openSession();
    try {
      TutorMapper mapper = sqlSession.getMapper(TutorMapper.class);
      return mapper.selectTutorById(tutorId);
    } finally {
      sqlSession.close();
    }
  }
}
