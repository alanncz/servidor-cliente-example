
import alann.tcc.api.Configuration;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alann
 */
public class ServerApp {
    
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException {
        
        Configuration();
        
        Service obj = new ServiceIMPL();
        Service stub = (Service) UnicastRemoteObject.exportObject(obj, 0);
        Registry registry = LocateRegistry.createRegistry(1111);
        registry.bind("Service", stub);
        System.out.println("Servidor do cliente rodando");
    }

    public static void Configuration() throws RemoteException, NotBoundException {
        
        String idApp = "c8e8c2ea8e6f421b8a4b7a4ce63a8171";
        String hostCollector = "localhost";
        Configuration.appConfiguration(idApp);
        Configuration.configurationCollector(hostCollector);
        
    }
    
}
