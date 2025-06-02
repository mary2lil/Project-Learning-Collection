/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package readinglistmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class ReadingListManager {

    /**
     * @param args the command line arguments
     */
    static void menu(){System.out.println("""
What would you like to do?

1. Add a new book
2. Remove a book
3. Display all books
4. Search for a book
5. Show the number of books
6. Clear your reading list
7. Exit
""");
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        ArrayList <Book> listOfBooks = new  ArrayList<>();
       
        //Read from file
         BufferedReader read = new BufferedReader(new FileReader("BookList.txt"));
         String line ;
         while((line=read.readLine())!=null){
             String parts[] = line.split(",");
             if(parts.length>=2){
                 String name = parts[0];
                 String author = parts[1];
                 listOfBooks.add(new Book(name,author));
             }
         }read.close();
        
         
        
        System.out.println("Hello! I hope you're doing well.");
        while(true){
            System.out.println("\n==================================");
            try{
            menu();
            int enter = in.nextInt();
            in.nextLine();
            if(enter==7){
                System.out.println("Goodbye! See you next time.");
                //Save file before exit
                 try{
         BufferedWriter write = new BufferedWriter(new FileWriter("BookList.txt"));
                   for(Book book : listOfBooks){
                       write.write(book.getName()+","+book.getAuthor());
                       write.newLine();
                   }
                   write.flush();
                   write.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
                return;
            }
            switch(enter){
                case 1 :{
                    System.out.println("Please enter the name of the book:");
                    String name = in.nextLine();
                    System.out.println("Now enter the author's name:");
                    String author = in.nextLine();
                    listOfBooks.add(new Book(name,author));
                    System.out.println("The book was added successfully.");
                    break;
                }
                case 2 : {
                    System.out.println("Enter the name of the book you want to remove:");
                    String name = in.nextLine();
                    Iterator <Book>iterator = listOfBooks.iterator();
                    boolean found = false;
                    while(iterator.hasNext()){
                        Book book = iterator.next();
                       if(book.getName().equals(name)) {
                           iterator.remove();
                           System.out.println("The book was removed successfully.");
                           found = true ;
                       }
                    }
                    if(!found){
                        System.out.println("No book with that name was found in your list.");
                    }
                    break;
                }
                case 3 : {
                    if(listOfBooks.isEmpty()){
                        System.out.println("Your reading list is currently empty.");
                    }
                    else{
                        for(Book book:listOfBooks){
                        System.out.println(book.toString());
                    }
                    }
                    break;
                }
                case 4 : {
                    if(listOfBooks.isEmpty()){
                        System.out.println("You can't search. Your list is empty");
                    }
                    else{
                    System.out.println("Enter the name of the book you want to search for:");
                    boolean found = false ;
                    String name = in.nextLine();
                    for(Book book : listOfBooks){
                        if(book.getName().equalsIgnoreCase(name)){
                            System.out.println(book.toString());
                            found = true ;
                        }
                    }
                    if(!found){
                        System.out.println("Sorry, no matching book was found in your list.");
                    }
                    }
                    break;
                }
                case 5 : {
                    System.out.println("You currently have "+listOfBooks.size()+"  books in your reading list");
                    break;
                }
                case 6 : {
                    listOfBooks.clear();
                    System.out.println("Your reading list has been cleared.");
                    break;
                }
                default:
                    System.out.println("Invalid input. Please try again.");
            }
            }catch(InputMismatchException e ){
                System.out.println("Invalid input. Please try again.");
                in.nextLine();
            }catch(Exception e ){
                System.out.println(e.getMessage());
            }
        }
        
       
    }
    
}
