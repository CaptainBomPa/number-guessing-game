package com.example.basics;

public class Guess {
    public int secretNumber;
    int approaches = 5;

    public Guess()
    {
        this.secretNumber = 1 + (int)(Math.random() * ((100 - 1) + 1));
    }
}
