/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basico;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 3268i
 */
public class Inicio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ExecutorService executor = Executors.newFixedThreadPool(5); // creamos un servicio de ejecución. lo que sería la "piscina" de procesos, con el limite de procesos a correr a la vez.
                                                                    // si ponemos limite 3 y le metemos 5 procesos, lanzara solo 3, y cuando vayan acabando, se van ejecutando los siguientes.
        
        for (int i = 0; i < 10; i++) { // ponemos que hay 10 procesos pendientes de ejecución / 10 procesos para lanzar. Se lanzan todos dentro.
            executor.execute(new Proceso()); // ejecutamos dentro de la piscina dicho proceso
        }
        
        executor.shutdown(); // hacemos una petición de apagar dicha piscina
        
        while(!executor.isTerminated()){} // nos quedamos esperando hasta que termine
        
        System.out.println("Todos los procesos han terminado."); // avisamos que ya hemos terminado 
    }
    
}
