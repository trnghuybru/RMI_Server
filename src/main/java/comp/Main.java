package comp;

import comp.Rmi.model.Station;
import comp.Rmi.model.Train;
import comp.Rmi.rmi.TrainService;
import comp.Rmi.rmi.TrainServiceImpl;
import comp.Rmi.rmi.TuyenService;
import comp.Rmi.rmi.TuyenServiceImpl;
import comp.trainticketserver.DAO.StationDAO;
import comp.trainticketserver.DAO.TrainDAO;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {

            LocateRegistry.createRegistry(1099);

            TrainService trainService = new TrainServiceImpl();

            Naming.rebind("rmi://localhost:1099/TrainService", trainService);

            System.out.println("Service is running...");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
