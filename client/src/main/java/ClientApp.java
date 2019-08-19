
import alann.tcc.api.Configuration;
import alann.tcc.api.RegistryId;
import alann.tcc.api.RegistryIdManager;
import alann.tcc.shared.model.TypeData;
import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
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
public class ClientApp {

    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException, UnknownHostException, IOException {

        Configuration();

        for (int k = 0; k < 50; k++) {

            RegistryId rid = RegistryIdManager.newRegistryId("Main", ListTypeData());
            RegistryId rid2 = RegistryIdManager.newRegistryId("NumerosPrimos", ListTypeData());

            NewPessoa np = new NewPessoa();
            NumerosPrimos numerosPrimos = new NumerosPrimos();

            np.newPessoa(rid);
            numerosPrimos.numerosPrimos(rid2);

            //View view = MonitoringServices.getVIew(cc.getAppReference());
        }
    }

    public static void Configuration() throws RemoteException, NotBoundException {

        String idApp = "c8e8c2ea8e6f421b8a4b7a4ce63a8171";
        String hostCollector = "localhost";
        Configuration.appConfiguration(idApp);
        Configuration.configurationCollector(hostCollector);

    }

    public static ArrayList<TypeData> ListTypeData() {
        ArrayList<TypeData> registryIds = new ArrayList();
        registryIds.add(TypeData.TaskTime);
        registryIds.add(TypeData.TaskMemory);
        registryIds.add(TypeData.TaskSizePacket);
        registryIds.add(TypeData.TaskThread);
        return registryIds;
    }

}
