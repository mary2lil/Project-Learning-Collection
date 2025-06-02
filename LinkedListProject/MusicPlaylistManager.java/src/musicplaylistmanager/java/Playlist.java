/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package musicplaylistmanager.java;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Playlist {
    LinkedList<Song>songs = new LinkedList<>();
    Scanner in = new Scanner(System.in);
    
    public void addSong(){
        System.out.println("Enter the title of the song:");
                        String title = in.nextLine();
                        System.out.println("Enter the artist name:");
                        String artist = in.nextLine();
                        System.out.println("Enter the duration (e.g., 3:45):");
                        String duration = in.nextLine();
                        songs.add(new Song(title,artist,duration));
                        System.out.println("The song has been added to your playlist.");
        
    }
    
     public void removeSong(){
        if(songs.isEmpty()){
            System.out.println("You can't remove , your list is empty");
               return ; 
        }
          Iterator<Song> iterator = songs.iterator();
           boolean found = false;
          System.out.println("Enter the title of the song you want to remove:");
           String title = in.nextLine();
          while(iterator.hasNext()){
           Song song = iterator.next();
           if(song.getTitle().equals(title)){
           System.out.println(song.toString());
           iterator.remove();
           System.out.println("Song removed successfully.");
          found = true;
            }             
            }
          if(!found){
           System.out.println("No song found with that title.");
                        }
    }
    
    
     public void displayAllSongs(){
          if(songs.isEmpty()){
              System.out.println("Your playlist is currently empty.");
               return ;
          }
         System.out.println("Your playlist:");
           for(Song song : songs){
            System.out.println(song.toString());
         }
    }
     
     public void clearPlayList(){
             songs.clear();
            System.out.println("All songs have been removed from your playlist.");
     }
     
     public void sizeOfPlayList(){
         System.out.println("You currently have " + songs.size() + " song(s) in your playlist.");
     }
     
     public void makeCopy(){
         
                        Song[] songsArray=songs.toArray(new Song[0]);
                        if(songsArray.length==0){
                            System.out.println("Your playlist is empty. No copy created.");
                        }
                        else{
                         System.out.println("A copy of your playlist has been created successfully.");
                        for(Song song : songsArray){
                            System.out.println(song.toString());
                        }
                        }
                       
     }
}
