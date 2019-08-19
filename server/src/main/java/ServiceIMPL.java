
import alann.tcc.api.MonitoringServicesMemory;
import alann.tcc.api.MonitoringServicesSizePacket;
import alann.tcc.api.MonitoringServicesThread;
import alann.tcc.api.MonitoringServicesTime;
import alann.tcc.api.RegistryId;
import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alann
 */
public class ServiceIMPL implements Service {

    private static ArrayList<Pessoa> pessoas = new ArrayList();
    private static ArrayList<List> listas = new ArrayList();
    private static ArrayList<String[]> lixo = new ArrayList();
    

    public ServiceIMPL() {
    }

    @Override
    public void newPessoa(RegistryId rid, Pessoa pessoa) throws RemoteException {

        try {

            MonitoringServicesTime.endTime(rid);
            MonitoringServicesMemory.initMemory(rid);
            MonitoringServicesThread.initQtdaThread(rid);

            Pessoa p = new Pessoa();
            p.setNome(pessoa.getNome());
            p.setIdade(pessoa.getIdade());
            pessoas.add(p);
           
            memoria();

            MonitoringServicesSizePacket.dataInput(rid, pessoa, true, true);
            MonitoringServicesMemory.endMemory(rid, true);
            MonitoringServicesThread.endQtdaThread(rid, true);

        } catch (IOException ex) {
            Logger.getLogger(ServiceIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<Integer> numerosPrimos(RegistryId rid, Integer numero) throws RemoteException {

        try {

            MonitoringServicesTime.endTime(rid);
            MonitoringServicesMemory.initMemory(rid);
            MonitoringServicesSizePacket.dataInput(rid, numero, false , false);
            MonitoringServicesThread.initQtdaThread(rid);

            ArrayList<Integer> lista = new ArrayList();
            for (int i = 2; i <= numero; i++) {
                if (isPrimo(i)) {
                    lista.add(i);
                }
            }
            
            memoria();

            listas.add(lista);
            
            MonitoringServicesSizePacket.dataOutPut(rid, lista, false, false);
            MonitoringServicesMemory.endMemory(rid, false);
            MonitoringServicesThread.endQtdaThread(rid, true);
            
            return lista;

        } catch (UnknownHostException ex) {
            Logger.getLogger(ServiceIMPL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServiceIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;

    }

    public static boolean isPrimo(int valor) {
        for (int j = 2; j < valor; j++) {
            if (valor % j == 0) {
                return false;
            }
        }
        return true;
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
