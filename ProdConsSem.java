
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriele
 */
public class ProdConsSem {
    private static Semaphore vuoto = new Semaphore(1);
    private static Semaphore pieno = new Semaphore(0);
    private static int buffer = 0;
    
   static public class Produttore extends Thread{
    
    public void run(){
        while(true){
            try{
                vuoto.acquire();
                buffer ++;
                System.out.println("Produce: " + buffer);
                pieno.release();
            }catch(InterruptedException e){
                
            }
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch(InterruptedException e){
                
            }
        }
    }
}
   static public class Consumatore extends Thread{
       int j = 0;
       
       public void run(){
           while(true){
               try{
                   pieno.acquire();
                   j = buffer; //sezione critica (consuma)ù
                   System.out.println("Consuma: " + j);
                   vuoto.release();
               }catch(InterruptedException e){
                   
               }
           }
       }
   }
}
