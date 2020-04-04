package jdbc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class sql {
    ArrayList<book> Books;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    static final String USER = "root";
    static final String PASS = "zhaobo123..";
    Connection conn = null;
    Statement stmt = null;


    sql(){
        Books = new ArrayList<book>();
    }

    public void register(){
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMsg() throws SQLException {
        this.register();
        String sql = "select * from book";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            book books = new book();
            books.setBook_id(rs.getString(1));
            books.setBook_name(rs.getString(2));
            books.setPrice(rs.getString(3));
            books.setAuthor(rs.getString(4));
            books.setPublisher(rs.getString(5));
            books.setDate(rs.getString(6));
            books.setNumber(rs.getInt(7));
            Books.add(books);
        }

    }

    public void getMsgByName(String name, JFrame jf, JTable table) throws SQLException {
        String sql = "select * from book where book__name like " + "'%" + name + "%'";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        Vector<String> head = new Vector<String>();
        head.add("图书编号");
        head.add("书名");
        head.add("作者");
        head.add("出版社");
        head.add("价格(RMB)");
        head.add("出版日期");
        head.add("剩余数量");
        Vector< Vector<Object> > all = new Vector<Vector<Object>>();
        while (rs.next()){
            Vector<Object> item = new Vector<Object>();
            item.add(rs.getString(1));
            item.add(rs.getString(2));
            item.add(rs.getString(4));
            item.add(rs.getString(5));
            item.add(rs.getString(3));
            item.add(rs.getString(6));
            item.add(rs.getString(7));
            all.add(item);
        }
        DefaultTableModel dtms = new DefaultTableModel(all, head);
        table.setModel(dtms);
    }

    public void updateMsg(String id, String book_name, String price, String author, String publisher, String date, int num) throws SQLException {
        String sql ="UPDATE book SET   book__name = " + "'" + book_name + "'" + ",price ="+  "'" + price + "'" + ", author = " + "'"+ author + "'"+ ", publisher = "+ "'"+ publisher
                + "'"+ ", publisher_date = "+ "'" +  date + "'"+", number = "+ "'"+ num+ "' where book_id = " +"'" + id +"';";
        stmt.executeUpdate(sql);

    }

    public void moveMsg(String id) throws SQLException {
        String sql = "delete from book where book_id = " + "'" + id + "';";
        stmt.executeUpdate(sql);
    }
    public void insertMsg(String id, String book_name, String price, String author, String publisher, String date, int num) throws SQLException {
        String sql = "insert into book VALUES ( " + "'" + id + "',"  + "'" + book_name + "'," + "'" + price + "'," + "'" + author + "',"
                + "'" + publisher + "'," + "'" + date + "'," + "'" + num + "');";
        stmt.executeUpdate(sql);
    }



}
