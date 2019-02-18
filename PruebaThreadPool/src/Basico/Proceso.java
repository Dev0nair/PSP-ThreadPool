/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basico;

/**
 *
 * @author 3268i
 */
public class Proceso implements Runnable{
    @Override
    public void run(){
        System.out.println("[ " + Thread.currentThread().getName() + " ] - Start.");
        processCommand();
        System.out.println("[ " + Thread.currentThread().getName() + " ] - End.");
        
    }
    
    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
