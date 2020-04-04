package jdbc;

public class book {
    private String book_id;     // 图书编号
    private String book_name;   // 书名
    private String price;       // 价格
    private  String author;      // 作者
    private String publisher;   // 出版社
    private  String date;        // 出版日期
    private  int number;         // 书的剩余数量

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBook_id() {
        return book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDate() {
        return date;
    }

    public int getNumber() {
        return number;
    }
}
