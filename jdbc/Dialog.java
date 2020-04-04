package jdbc;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Dialog extends JDialog{
    JTextField book_id;
    JTextField book_name;
    JTextField price;
    JTextField author;
    JTextField publisher;
    JTextField date;
    JTextField number;
    JPanel panel;
    String id, name,pr, au, pu, da, num;
    JButton jButton, close;
    String Id;
    sql msg;
    windwosFace face;
    Dialog(windwosFace jf){
        super(jf,"添加信息", false);
    }
    Dialog(windwosFace jf, String na, String id, sql msg){
       super(jf,"修改资料", false);
       Id = id;
       face = jf;
       this.msg = msg;
       this.setLayout(new BorderLayout());
       JPanel pa  = new JPanel();
       pa.add(new JLabel("您要修改的图书名称：" + na + "          图书id为：" + id));
       panel = new JPanel();
       this.setSize(900,150);
        this.setLocationRelativeTo(null);
       panel.add(new JLabel("书名:"));
       book_name = new JTextField(8);
       panel.add(book_name);
       panel.add(new JLabel("作者:"));
       author = new JTextField(8);
       panel.add(author);
       panel.add(new JLabel("出版社:"));
       publisher = new JTextField(8);
       panel.add(publisher);
       panel.add(new JLabel("价格"));
       price = new JTextField(8);
       panel.add(price);
       panel.add(new JLabel("出版日期"));
       date = new JTextField(8);
       panel.add(date);
       panel.add(new JLabel("图书总数量"));
       number = new JTextField(10);
       panel.add(number);
       JPanel jp = new JPanel();
       jButton = new JButton("修改");
       close = new JButton("返回");
       jp.add(jButton);
       jp.add(new JLabel("                 "));
       jp.add(close);
       this.add(pa,BorderLayout.NORTH);
       this.add(panel, BorderLayout.CENTER);
       this.add(jp, BorderLayout.SOUTH);
       this.setVisible(true);
       createLister();
    }

    public void addMessage(windwosFace jf, sql msg){
        this.msg = msg;
        this.setLayout(new BorderLayout());
        panel = new JPanel();
        this.setSize(1000,100);
        this.setLocationRelativeTo(null);
        panel.add(new JLabel("图书编号"));
        book_id = new JTextField(8);
        panel.add(book_id);
        panel.add(new JLabel("书名:"));
        book_name = new JTextField(8);
        panel.add(book_name);
        panel.add(new JLabel("作者:"));
        author = new JTextField(8);
        panel.add(author);
        panel.add(new JLabel("出版社:"));
        publisher = new JTextField(8);
        panel.add(publisher);
        panel.add(new JLabel("价格"));
        price = new JTextField(8);
        panel.add(price);
        panel.add(new JLabel("出版日期"));
        date = new JTextField(8);
        panel.add(date);
        panel.add(new JLabel("图书总数量"));
        number = new JTextField(10);
        panel.add(number);
        JPanel jp = new JPanel();
        jButton = new JButton("添加");
        close = new JButton("返回");
        jp.add(jButton);
        jp.add(new JLabel("                 "));
        jp.add(close);
        this.add(panel, BorderLayout.CENTER);
        this.add(jp, BorderLayout.SOUTH);
        this.setVisible(true);
        close.addActionListener(actionEvent -> {
            this.setVisible(false);
        });
        jButton.addActionListener(actionEvent -> {
            getAddmsg();
            try {
                msg.insertMsg(id,name, pr, au, pu, da, Integer.parseInt(num));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                jf.showMsg();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.setVisible(false);
        });

    }

    public void getAddmsg(){
        id = book_id.getText();
        name = book_name.getText();
        pr = price.getText();
        au = author.getText();
        pu = publisher.getText();
        da = date.getText();
        num = number.getText();
    }

    public void getString() throws SQLException {
        //id = book_id.getText();
        name = book_name.getText();
        pr = price.getText();
        au = author.getText();
        pu = publisher.getText();
        da = date.getText();
        num = number.getText();
        msg.updateMsg(Id, name, pr, au, pu, da, Integer.parseInt(num));


    }

    public void createLister(){
        jButton.addActionListener(actionEvent -> {
            try {
                getString();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Dialog.this.setVisible(false);
            JOptionPane.showMessageDialog(null, "修改信息成功", "OK", JOptionPane.QUESTION_MESSAGE);
            try {
                face.showMsg();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        close.addActionListener(actionEvent -> {
            this.setVisible(false);
        });
    }


}
