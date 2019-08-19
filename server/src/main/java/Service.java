
import alann.tcc.api.RegistryId;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alann
 */
public interface Service extends Remote {
    
    public void newPessoa(RegistryId rid, Pessoa pessoa)throws RemoteException;
    public ArrayList<Integer> numerosPrimos(RegistryId rid, Integer numero)throws RemoteException;
    
    
}
