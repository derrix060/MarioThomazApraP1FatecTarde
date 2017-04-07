package com.example.mario.mariothomazaprap1fatectarde;

import java.util.ArrayList;

/**
 * Created by mario on 07/04/17.
 */

public class Cart {
    private ArrayList<Book> books = new ArrayList<>();
    private double total = 0;
    private int actualIdx = 0;

    public Cart (){}

    public void addItem(Book book){
        books.add(book);
        this.total += book.price;

    }

    public void addItem(Book book, int amount){
        books.add(book);
        this.total += (book.price * amount);

    }

    public double getTotal(){
        return this.total;
    }

    public int getItensAmount(){
        return this.books.size();
    }

    public Book prevBook(){
        actualIdx --;

        if (actualIdx < 0)
            actualIdx = books.size() -1;

        return actualBook();
    }

    public Book nextBook(){

        actualIdx++;

        if(actualIdx == books.size())
            actualIdx = 0;

        return actualBook();
    }

    public Book actualBook(){
        return books.get(actualIdx);
    }
}
