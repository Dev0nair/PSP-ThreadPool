/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Avanzado;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author 3268i
 */
public class Inicio {
    
    public static void main(String args[]) throws InterruptedException{

        // objeto que tiene el evento que salta cuando se rechaza un proceso
        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
        
        // hacemos el trato del proceso, que por defecto lo ejecute como hilo en vez de Runnables
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        
        
        // arg1: procesos que se ejecutaran a la vez.
        // arg2: numero maximo de procesos que habrán en la piscina
        // arg3: tiempo máximo que un exceso de procesos inactivos esperará para nuevas tareas antes de terminar ?????
        // arg4: unidad de tiempo, que lo estableceremos en segundos
        // arg5: cola donde se almacenaran los procesos lanzados por "execute", antes de ser ejecutados. Es decir, cuando se quedan en espera para poder ser ejecutados, se quedan ahí
        // arg6: es el encargado de transformar esos procesos Runnable, en Threads. Así como parametro que trata el proceso que le estamos dando antes de ejecutarlo
        // arg7: objeto que contiene el evento que ocurre cuando se rechaza un hilo en la piscina.
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(1, 5, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3), threadFactory, rejectionHandler);
        
        // monitor que nos aporta la información de la piscina que estamos ejecutando, pasandole la piscina, y el numero de segundos por el que se va mostrar la información actual
        MyMonitorThread monitor = new MyMonitorThread(executorPool, 1);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
        
        
        // empezamos a lanzar los procesos
        int numProcesos = 10;
        for(int i=1; i<= numProcesos; i++){
            executorPool.execute(new Proceso("Proceso " + i));
        }
        
        // hacemos una peticion de apagado a la piscina para dejar que se finalicen los procesos ya lanzados
        executorPool.shutdown();
        
        while(!executorPool.isTerminated()) {} // esperamos a que se terminen
        
        Thread.sleep(1500);
        monitor.shutdown(); // paramos el monitor
        
    }
    
}
