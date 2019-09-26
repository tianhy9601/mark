package JDBCUtils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class BaseDao {

    private Connection conn=null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;

    private static String username;
    private static String password;
    private static String url;
    private static String Driver;

    private static DruidDataSource ds;

    static{

        Properties pt=new Properties();
        try {
            pt.load(BaseDao.class.getClassLoader().getResourceAsStream("jdbcs.properties"));

            username=pt.getProperty("username");
            password=pt.getProperty("password");
            url=pt.getProperty("url");
            Driver=pt.getProperty("driver");

            ds=new DruidDataSource();

            ds.setDriverClassName(Driver);
            ds.setUrl(url);
            ds.setUsername(username);
            ds.setPassword(password);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void myConnection(){
        try {
            conn=ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void myClos(){
        try {
            if(conn!=null){
                conn.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int myExecUpdate(Object[] obj,String sql){

        myConnection();

        try {
            ps=conn.prepareStatement(sql);

            if(obj!=null){
                for (int i = 0; i <obj.length ; i++) {
                    ps.setObject(i+1,obj[i]);
                }
            }

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            myClos();
        }

        return 0;

    }

    public ResultSet myQuery(Object[] obj,String sql){

        myConnection();

        try {
            ps=conn.prepareStatement(sql);
            if(obj!=null){
                for (int i = 0; i <obj.length ; i++) {
                    ps.setObject(i+1,obj[i]);
                }
            }

            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
