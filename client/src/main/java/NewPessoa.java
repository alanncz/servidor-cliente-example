
import alann.tcc.api.MonitoringServicesMemory;
import alann.tcc.api.MonitoringServicesSizePacket;
import alann.tcc.api.MonitoringServicesThread;
import alann.tcc.api.MonitoringServicesTime;
import alann.tcc.api.RegistryId;
import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alann
 */
public class NewPessoa {
    
    private static ArrayList<String[]> lixo = new ArrayList();
    
    public void newPessoa(RegistryId rid) throws UnknownHostException, 
            RemoteException, NotBoundException, IOException, InterruptedException{
        
        MonitoringServicesThread.initQtdaThread(rid);
        MonitoringServicesTime.initTime(rid);
        MonitoringServicesMemory.initMemory(rid);
        
        Thread t1 = new Thread();
        Thread t2 = new Thread();
        t1.start();
        t2.start();
        
        MonitoringServicesThread.contNewThread(rid, 2);
        
        Registry registry = LocateRegistry.getRegistry(1111);
        Service stub = (Service) registry.lookup("Service");
        
        Pessoa p = new Pessoa();
        p.setNome("Alann Rodrigues Ferreira");
        p.setCpf("096.601.104-05");
        p.setIdade(25);
        
        memoria();
        
        MonitoringServicesSizePacket.dataOutPut(rid, p,true, false);
        MonitoringServicesMemory.endMemory(rid, false);
        MonitoringServicesThread.endQtdaThread(rid, false);
        
        stub.newPessoa(rid, p);
        

        Thread.sleep(1000);
        
    }
    
    public int aleatoriar(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
    }
    
    private void memoria() {

        int qtda = aleatoriar(10000, 20000);
        String[] lista = new String[qtda];

        for (int i = 0; i < qtda; i++)
            lista[i] = new String("X");
        
        lixo.add(lista);

    }
    
}
