package comp;

import comp.Rmi.model.CTHDDetailsDTO;
import comp.Rmi.rmi.*;
import comp.trainticketserver.DAO.AdminDAO;
import comp.trainticketserver.DAO.HoaDonDAO;
import comp.trainticketserver.DAO.TicketDAO;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
//            System.setProperty("java.rmi.server.hostname", "172.20.10.5");
            LocateRegistry.createRegistry(1099);

            TrainService trainService = new TrainServiceImpl();
            CarriageService carriageService = new CarriageServiceImpl();
            PriceService priceService = new PriceServiceImpl();
            SeatService seatService = new SeatServiceImpl();
            StationService stationService = new StationServiceImpl();
            HoaDonService hoaDonService = new HoaDonServiceImpl();
            LoginService loginService = new LoginServiceImpl();
            TicketService ticketService = new TicketServiceImpl();
            TuyenService tuyenService = new TuyenServiceImpl();
            StatictisService statictisService = new StatictisServiceImpl();

            Naming.rebind("rmi://localhost:1099/TrainService", trainService);
            Naming.rebind("rmi://localhost:1099/CarriageService", carriageService);
            Naming.rebind("rmi://localhost:1099/PriceService", priceService);
            Naming.rebind("rmi://localhost:1099/SeatService", seatService);
            Naming.rebind("rmi://localhost:1099/StationService", stationService);
            Naming.rebind("rmi://localhost:1099/HoaDonService", hoaDonService);
            Naming.rebind("rmi://localhost:1099/LoginService", loginService);
            Naming.rebind("rmi://localhost:1099/TicketService", ticketService);
            Naming.rebind("rmi://localhost:1099/TuyenService", tuyenService);
            Naming.rebind("rmi://localhost:1099/StatictisService", statictisService);

            System.out.println("Service is running...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
