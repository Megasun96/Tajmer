/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tajmer;

/**
 *
 * @author Dimitrijevic
 */
class Vreme {
     public static boolean vreme (String time){
       
    String [] parsiranje = time.split(":");
     int hours = Integer.valueOf(parsiranje [0]);
      int minutes = Integer.valueOf(parsiranje [1]);
      int seconds = Integer.valueOf(parsiranje [2]);
 
 if 
         (hours <0 || hours > 23)  
 {
     return false;
 }
 else if
         (minutes <0 || minutes >59) 
 {
     return false;
 }
 else if 
         (seconds <0 || seconds >59)
 {
     return false;
 }
 else
 {
     return true;
 }
 }       

    
    
}