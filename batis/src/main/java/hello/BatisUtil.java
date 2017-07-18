package hello;

import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import hello.domain.Student;
import hello.mappers.StudentMapper;
import hello.typehandlers.PhoneTypeHandler;

public class BatisUtil {
  private static final String DEFAULT_MYBATIS_CONFIG_FILE = "mybatis-all-config.xml";
  private static SqlSessionFactory sqlSessionFactory;

  public static SqlSessionFactory getSqlSessionFactoryUsingXML() {
    if (sqlSessionFactory == null) {
      try (InputStream inputStream = Resources.getResourceAsStream(DEFAULT_MYBATIS_CONFIG_FILE)) {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream/*, "development"*/);
      } catch (IOException e) {
        throw new RuntimeException(e.getCause());
      }
    }
    return sqlSessionFactory;
  }

  public static SqlSessionFactory getSqlSessionFactoryUsingJavaAPI() {
    if (sqlSessionFactory == null) {
      try {
        DataSource dataSource = DataSourceFactory.getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.getTypeAliasRegistry().registerAlias("student", Student.class);
        configuration.getTypeHandlerRegistry().register(PhoneTypeHandler.class);
        configuration.addMapper(StudentMapper.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return sqlSessionFactory;
  }

  public static SqlSession openSession() {
    return getSqlSessionFactoryUsingXML().openSession();
  }
}
