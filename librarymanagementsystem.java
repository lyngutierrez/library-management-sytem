import java.util.*;


interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
}

 class Book implements LibraryItem {
    private String title;
    private String author;
    private String publicationYear;
    private boolean isBorrowed;

    public Book(String title, String author, String publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isBorrowed = false;
    }
    
    @Override
    public String toString() {
        return title +  " by "  +  author;
    }

    @Override
    public void borrowItem() {
        isBorrowed = true;
    }

    @Override
    public void returnItem() {
        isBorrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return isBorrowed;
    }
}

class DVD implements LibraryItem {
    private String title;
    private String director;
    private boolean isBorrowed;

    public DVD(String title, String director) {
        this.title = title;
        this.director = director;
        this.isBorrowed = false;
    }

    public String toString() {
        return title +  " by "  +  director;
        
    }


    @Override
    public void borrowItem() {
        isBorrowed = true;
    }

    @Override
    public void returnItem() {
        isBorrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return isBorrowed;
    }
}

abstract class LibraryUser {
    public void borrowItem(LibraryItem item) {
        item.borrowItem();
    }

    public void returnItem(LibraryItem item) {
        item.returnItem();
    }

    public abstract void printItemsBorrowed();
}

class Student extends LibraryUser {
    private List<LibraryItem> borrowedItems;

    public Student() {
        borrowedItems = new ArrayList<>();
    }

    @Override
    public void printItemsBorrowed() {
        System.out.println("Borrowed items by student: ");
        for (LibraryItem item : borrowedItems) {
            System.out.println(item.getClass().getSimpleName() + ": " + item.toString());
        }
    }

    @Override
    public void borrowItem(LibraryItem item) {
        super.borrowItem(item);
        borrowedItems.add(item);
    }

    @Override
    public void returnItem(LibraryItem item) {
        super.returnItem(item);
        borrowedItems.remove(item);
    }
}

 class Teacher extends LibraryUser {
    private List<LibraryItem> borrowedItems;

    public Teacher() {
        borrowedItems = new ArrayList<>();
    }

    @Override
    public void printItemsBorrowed() {
        System.out.println("Borrowed items by teacher: ");
        for (LibraryItem item : borrowedItems) {
            System.out.println(item.getClass().getSimpleName() + ": " + item.toString());
        }
    }

    @Override
    public void borrowItem(LibraryItem item) {
        super.borrowItem(item);
        borrowedItems.add(item);
    }

    @Override
    public void returnItem(LibraryItem item) {
        super.returnItem(item);
        borrowedItems.remove(item);
    }
}

public class librarymanagementsystem{
    public static void main(String[] args) {
        Book book1 = new Book("Marley and Me", "John Grogan", "2005");
        Book book2 = new Book("Haikyu", "Haruichi Furudate", "2020");
        DVD dvd1 = new DVD("Star Wars: The Rise of Skywalker", "J.J. Abrams");
        DVD dvd2 = new DVD("Star Wars: The Last Jedi", "Rian Johnson");

        Student student = new Student();
        Teacher teacher = new Teacher();

        student.borrowItem(book1);
        teacher.borrowItem(dvd1);

        student.printItemsBorrowed();
        teacher.printItemsBorrowed();

        student.returnItem(book1);

        student.printItemsBorrowed();
    }
}