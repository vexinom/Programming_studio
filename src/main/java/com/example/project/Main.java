package com.example.project;
import java.util.Random;
import java.util.Scanner;

public class Main 
{
    public static void main( String[] args )
    {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        int n = rand.nextInt(50);
        int guess = 69;

        n += 1;

        while(guess != n)
        {
            System.out.println( "Provide some number from range 1 to 50: ");
            guess = scanner.nextInt();

            if(guess < n)
            {
                System.out.println( "The number is too small");
            }
            else if(guess > n)
            {
                System.out.println( "The number is too big");
            }
            else
            {
                System.out.println( "Congrats, you've gueesed " + n);
            }
        }

        
    }
}
