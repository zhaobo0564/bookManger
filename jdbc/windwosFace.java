package jdbc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;


public class windwosFace extends JFrame {
    JTextArea text;
    sql msg;
    Container c;
    DefaultTableModel dtm;
    JTable table;
    JPanel panel;
    JTextField searchBook;
    JButton search;
    JButton showAll;
    JPopupMenu popupMenu;
    JMenuItem  update;
    JMenuItem  move;
    String name, id;
    JButton addMsg;
    int pos;
    Vector<String>q;

    windwosFace() throws SQLException {
        msg = new sql();
        msg.setMsg();
        this.setTitle("图书馆里系统");
        this.setSize(800,800 );
        this.setLayout(new BorderLayout());
        initText();
        initPanel();
        createPop();
        createListener();
        this.add(panel, BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public  void initText(){
       // c = getContentPane();
        Vector<String> head = new Vector<String>();
        head.add("图书编号");
        head.add("书名");
        head.add("作者");
        head.add("出版社");
        head.add("价格(RMB)");
        head.add("出版日期");
        head.add("剩余数量");

        Vector< Vector<Object> > all = new Vector<Vector<Object>>();

        for(int i = 0; i < msg.Books.size(); i++){
            Vector<Object> item = new Vector<Object>();
            item.add(msg.Books.get(i).getBook_id());
            item.add(msg.Books.get(i).getBook_name());

            item.add(msg.Books.get(i).getAuthor());
            item.add(msg.Books.get(i).getPublisher());
            item.add(msg.Books.get(i).getPrice());
            item.add(msg.Books.get(i).getDate());
            item.add(msg.Books.get(i).getNumber());
            all.add(item);
        }
        dtm = new DefaultTableModel(all,head);
        table = new JTable();
        table.setModel(dtm);
        table.setEnabled(false);
        //c.add( new JScrollPane(table));
    }

    public void initPanel(){
        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("基本操作"));
        showAll = new JButton("显示所有图书");
        JPanel p = new JPanel();
        p.add(showAll);
        panel.add(p);
        JPanel p1 = new JPanel();
        searchBook = new JTextField(15);
        search = new JButton("查找图书");
        p1.add(searchBook);
        p1.add(search);
        JPanel p2 = new JPanel();
        addMsg = new JButton("添加信息");
        p2.add(addMsg);
        panel.add(p1);
        panel.add(p2);

    }

    public void createPop(){
        popupMenu = new JPopupMenu();
        update = new JMenuItem("更新信息");
        move = new JMenuItem("删除信息");
        popupMenu.add(update);
        popupMenu.add(move);
    }

    public void getMsg(){
        Vector<String>g = new Vector<String>();

        for(int i = 0; i < 7; i++){
            String v = table.getValueAt(pos - 1, i).toString();
            //System.out.println(v);
            g.add(v);
        }
        name = g.get(1);
        id =  g.get(0);

    }

    public void showMsg() throws SQLException {
        Vector<String> head = new Vector<String>();
        head.add("图书编号");
        head.add("书名");
        head.add("作者");
        head.add("出版社");
        head.add("价格(RMB)");
        head.add("出版日期");
        head.add("剩余数量");
        msg.Books.clear();
        msg.setMsg();
        Vector< Vector<Object> > all = new Vector<Vector<Object>>();

        for(int i = 0; i < msg.Books.size(); i++){
            Vector<Object> item = new Vector<Object>();
            item.add(msg.Books.get(i).getBook_id());
            item.add(msg.Books.get(i).getBook_name());

            item.add(msg.Books.get(i).getAuthor());
            item.add(msg.Books.get(i).getPublisher());
            item.add(msg.Books.get(i).getPrice());
            item.add(msg.Books.get(i).getDate());
            item.add(msg.Books.get(i).getNumber());
            all.add(item);
        }
        dtm = new DefaultTableModel(all, head);
        dtm.setDataVector(all, head);
        table.setModel(dtm);
    }

    public  void createListener(){
        search.addActionListener(actionEvent -> {

            try {
                msg.getMsgByName(searchBook.getText(), this, table);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        showAll.addActionListener(actionEvent -> {
            table.setModel(dtm);
        });

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(e.getButton() == MouseEvent.BUTTON3){
                    popupMenu.show(e.getComponent(),e.getX(),e.getY());
                    //获取鼠标点击的行的位置（及行数）
                    Point mousepoint;
                    mousepoint =e.getPoint();
                    pos = table.rowAtPoint(mousepoint) + 1;
                }


            }
        });


        update.addActionListener(actionEvent -> {
            getMsg();
            new Dialog(windwosFace.this, name, id, msg);
        });

        move.addActionListener(actionEvent -> {
            getMsg();
            try {
                msg.moveMsg(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                showMsg();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        addMsg.addActionListener(actionEvent -> {
            new Dialog(windwosFace.this).addMessage(windwosFace.this, msg);


        });
    }


}
