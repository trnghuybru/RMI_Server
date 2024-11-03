package comp;

import comp.Rmi.rmi.TrainService;
import comp.Rmi.rmi.TrainServiceImpl;
import comp.Rmi.rmi.TuyenService;
import comp.Rmi.rmi.TuyenServiceImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) {
        try {

            LocateRegistry.createRegistry(1099);

            TrainService trainService = new TrainServiceImpl();
            TuyenService tuyenService = new TuyenServiceImpl();

            Naming.rebind("rmi://localhost:1099/TuyenService", tuyenService);
            Naming.rebind("rmi://localhost:1099/TrainService", trainService);

            System.out.println("Service is running...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
