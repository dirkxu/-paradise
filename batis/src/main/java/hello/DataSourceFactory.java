package hello;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;

public class DataSourceFactory {
  private static final Properties PROPERTIES = new Properties();

  static {
    try {
      InputStream is = DataSourceFactory.class.getResourceAsStream("application.properties");
      PROPERTIES.load(is);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static DataSource getDataSource() {
    String driver = PROPERTIES.getProperty("jdbc.driverClassName");
    String url = PROPERTIES.getProperty("jdbc.url");
    String username = PROPERTIES.getProperty("jdbc.username");
    String password = PROPERTIES.getProperty("jdbc.password");
    return new PooledDataSource(driver, url, username, password);
  }

  public static DataSource getJNDIDataSource() {
    String dataSourceJNDIName = "java:comp/env/jdbc/MyBatisDemoDS";
    try {
      InitialContext ctx = new InitialContext();
      return (DataSource) ctx.lookup(dataSourceJNDIName);
    } catch (NamingException e) {
      throw new RuntimeException(e);
    }
  }
}
