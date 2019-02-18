/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Avanzado;

import java.util.concurrent.ThreadPoolExecutor;

public class MyMonitorThread implements Runnable
{
    private ThreadPoolExecutor executor;
    private int seconds;
    private boolean run=true;

    public MyMonitorThread(ThreadPoolExecutor executor, int delay)
    {
        this.executor = executor;
        this.seconds=delay;
    }
    public void shutdown(){
        this.run=false;
    }
    @Override
    public void run()
    {
        while(run){
                System.out.println(
                    String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                        this.executor.getPoolSize(), // numero de procesos dentro del pool
                        this.executor.getCorePoolSize(), // numero de procesos que pueden corren a la par
                        this.executor.getActiveCount(), // numero de procesos activos en el hilo
                        this.executor.getCompletedTaskCount(), // numero de procesos completados
                        this.executor.getTaskCount(), // numero de proceso que se pueden ejecutar en este pool segun el tamaño del pool mas el tamaño de la lista de espera
                        this.executor.isShutdown(), // si esta apagado
                        this.executor.isTerminated())); // si ha terminado todo
                try {
                    Thread.sleep(seconds*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
            
    }
}