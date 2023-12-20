package com.example.librarymanagmentsystem;

public class Book {
    private int bookId;
    private String bookName,category,author;
    private int copies;

    public Book(int bookId) {
        this.bookId = bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Book() {
    }

    public Book(int bookId, String bookName, String category, String author, int copies) {
        this.bookId = bookId;
        this.category = category;
        this.bookName = bookName;
        this.author = author;
        this.copies = copies;
    }

    public Book(String bookName, String category, String author, int copies) {
        this.bookName = bookName;
        this.category = category;
        this.author = author;
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "Book{" + "bookId=" + bookId + ", bookName=" + bookName + ", category=" + category + ", author=" + author + ", copies=" + copies + '}';
    }

}
