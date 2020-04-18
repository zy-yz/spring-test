package com.peppers;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName SpringTransactionExample
 * @Author peppers
 * @Date 2020/4/17
 * @Description
 **/
public class SpringTransactionExample {

    private static String url = "jdbc:mysql://121.196.205.196:3306/mydb";
    private static String user = "root";
    private static String password = "123456";

    public static Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://121.196.205.196:3306/mydb","root","123456");
        return conn;
    }

    public static void main(String[] args) {
        final DataSource ds = new DriverManagerDataSource(url,user, password);

        final TransactionTemplate template = new TransactionTemplate();

        template.setTransactionManager(new DataSourceTransactionManager(ds));

        template.execute(new TransactionCallback<Object>() {
            public Object doInTransaction(TransactionStatus status){
                Connection conn = DataSourceUtils.getConnection(ds);
                Object savePoint = null;
                try {
                    {
                        //插入
                        PreparedStatement prepare = conn.prepareStatement("insert INTO account (accountName,user,money) values (?,?,?)");
                        prepare.setString(1, "111");
                        prepare.setString(2, "aaaa");
                        prepare.setInt(3, 10000);
                        prepare.executeUpdate();
                        System.out.println("插入");

                    }
                    {
                        //更新
                        PreparedStatement prepare = conn.prepareStatement("UPDATE account set money = money + 1 where user = ?");
                        prepare.setString(1, "fadfa");

                       // int i = 1 / 0;
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }catch (Exception e){
                    System.out.println("更新失败");
                    if(savePoint != null){
                        status.rollbackToSavepoint(savePoint);
                    }else {
                        status.setRollbackOnly();
                    }
                }
                return null;

            }
        });
    }
}
