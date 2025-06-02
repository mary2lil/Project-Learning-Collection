/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package musicplaylistmanager.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class MusicPlaylistManagerJava {
    static void displayMenu(){
        System.out.println("""
======================================================
🎵  What would you like to do with your playlist?

    1. Add a new song
    2. View all songs
    3. Remove a song
    4. Clear the entire playlist
    5. Show total number of songs
    6. Create a copy of your playlist
    7. Exit

======================================================
""");
        
    }
    
    static User logIn(LinkedList<User> users,Scanner in){
        boolean logIn = false;
        int count = 0;
        User userLog = null ;
         while(count <3){
            System.out.println("Please enter your user ID:");
            String id = in.next();
            System.out.println("Now enter your password:");
            String password = in.next();
            for(User user : users){
            if(user.getId().equals(id)&&user.getPassword().equals(password)){
                userLog=user;
                logIn=true;
                break;
            }
        }
            if(logIn){
                System.out.println("Login successful! Welcome to your music playlist");
                break;
            }
            if(!logIn){
                System.out.println("Incorrect ID or password. Please try again");
                count++;
            }
            if(count==3){
                System.out.println("Too many failed attempts. Access denied.");
            }
    }
         return userLog;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedList<User>users = new LinkedList<>();
        Playlist playlist = new Playlist();
        try{
        BufferedReader readUsers = new BufferedReader(new FileReader("Users.txt"));
        String line ;
        while((line=readUsers.readLine())!=null){
            String setUsers [] = line.split(",");
            String id = setUsers[0];
            String password = setUsers[1];
            users.add(new User(id,password));
        }
        readUsers.close();
        }catch(FileNotFoundException e ){
            System.out.println("The file is not found");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
                
       User user = logIn(users, in);
        if(user!=null){
            while(true){
                try{
                displayMenu();
                int choose = in.nextInt();
                in.nextLine();
                if(choose==7){
                    System.out.println("Goodbye! Thanks for using the Music Playlist Manager");
                    return;
                }
                switch(choose){
                    case 1 :{
                        playlist.addSong();
                        break;
                }
                    case 2 :{
                         playlist.displayAllSongs();
                        break;
                    }
                    case 3 :{
                         playlist.removeSong();
                        break;
                    }
                    case 4 : {
                        playlist.clearPlayList();
                        break;
                    } 
                    case 5 :{
                        playlist.sizeOfPlayList();
                        break;
                    }
                    case 6 : {
                        playlist.makeCopy();
                        break;
                    }
                    default:
                        System.out.println("Please try again");
                }
            }catch(InputMismatchException e ){
                    System.out.println("Invalid input. Please enter a number from the menu");
                    in.nextLine();
            }
            }
        }
    }
    
}
