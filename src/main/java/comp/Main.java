package comp;

import comp.Rmi.rmi.*;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) {
        try {

            LocateRegistry.createRegistry(1099);

            TrainService trainService = new TrainServiceImpl();
            CarriageService carriageService = new CarriageServiceImpl();
            PriceService priceService = new PriceServiceImpl();
            SeatService seatService = new SeatServiceImpl();
            StationService stationService = new StationServiceImpl();

            Naming.rebind("rmi://localhost:1099/TrainService", trainService);
            Naming.rebind("rmi://localhost:1099/CarriageService", carriageService);
            Naming.rebind("rmi://localhost:1099/PriceService", priceService);
            Naming.rebind("rmi://localhost:1099/SeatService", seatService);
            Naming.rebind("rmi://localhost:1099/StationService", stationService);


            System.out.println("Service is running...");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
