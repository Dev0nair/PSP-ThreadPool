/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Avanzado;

import Basico.*;

/**
 *
 * @author 3268i
 */
public class Proceso implements Runnable{
    
    private String name;
    
    public Proceso(String name){
        this.name = name;
    }
    
    @Override
    public void run(){
        System.out.println("[ " + Thread.currentThread().getName() + " ] " + name + " - Start.");
        processCommand();
        System.out.println("[ " + Thread.currentThread().getName() + " ] " + name + " - End.");
        
    }
    
    private void processCommand() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString(){
        return this.name;
    }
    
}
