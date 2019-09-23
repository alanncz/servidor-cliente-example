
import alann.tcc.api.monitoring.MonitoringServicesMemory;
import alann.tcc.api.monitoring.MonitoringServicesSizePacket;
import alann.tcc.api.monitoring.MonitoringServicesThread;
import alann.tcc.api.monitoring.MonitoringServicesTime;
import alann.tcc.api.util.RegistryId;
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
public class NumerosPrimos {
    
    private static ArrayList<String[]> lixo = new ArrayList();

    public void numerosPrimos(RegistryId rid) throws UnknownHostException, RemoteException, NotBoundException, IOException {

        MonitoringServicesTime.initTime(rid);
        MonitoringServicesMemory.initMemory(rid);
        MonitoringServicesThread.initQtdaThread(rid);

        Thread t1 = new Thread();
        Thread t2 = new Thread();
        t1.start();
        t2.start();

        MonitoringServicesThread.contNewThread(rid, 2);

        Registry registry = LocateRegistry.getRegistry(1111);
        Service stub = (Service) registry.lookup("Service");

        int maximo = 20000;
        int minimo = 10000;
        
        int randomNum = aleatoriar(minimo, maximo);
        
        memoria();

        MonitoringServicesSizePacket.dataOutPut(rid, randomNum, false, false);
        MonitoringServicesThread.endQtdaThread(rid, false);

        ArrayList lista = stub.numerosPrimos(rid, randomNum);

        MonitoringServicesSizePacket.dataInput(rid, lista, false, true);
        MonitoringServicesMemory.endMemory(rid, true);

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
