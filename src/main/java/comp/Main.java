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

            Naming.rebind("rmi://localhost:1099/TrainService", trainService);
            Naming.rebind("rmi://localhost:1099/CarriageService", carriageService);
            Naming.rebind("rmi://localhost:1099/PriceService", priceService);
            Naming.rebind("rmi://localhost:1099/SeatService", seatService);
            Naming.rebind("rmi://localhost:1099/StationService", stationService);
            Naming.rebind("rmi://localhost:1099/HoaDonService", hoaDonService);
            Naming.rebind("rmi://localhost:1099/LoginService", loginService);
            Naming.rebind("rmi://localhost:1099/TicketService", ticketService);
            Naming.rebind("rmi://localhost:1099/TuyenService", tuyenService);

            System.out.println("Service is running...");

            AdminDAO adminDAO = new AdminDAO();

            // Tạo danh sách toa và giá tiền
            List<Map.Entry<Integer, Double>> toaPriceList = new ArrayList<>();
            toaPriceList.add(new AbstractMap.SimpleEntry<>(1, 150.0));  // ToaID = 1, giá tiền = 150.0
            toaPriceList.add(new AbstractMap.SimpleEntry<>(2, 200.0));  // ToaID = 2, giá tiền = 200.0
            toaPriceList.add(new AbstractMap.SimpleEntry<>(3, 250.0));  // ToaID = 3, giá tiền = 250.0

            // Các thông tin tàu và ga
            int tauID = 8;    // Giả sử ID của tàu là 123
            int gaDi = 1;       // Ga đi (ID)
            int gaDen = 2;      // Ga đến (ID)

            try {
                // Gọi phương thức để thêm giá tiền vào bảng 'banggia'
                adminDAO.themGiaTien(tauID, toaPriceList, gaDi, gaDen);
                System.out.println("Dữ liệu đã được chèn vào bảng banggia thành công!");
            } catch (SQLException e) {
                System.err.println("Có lỗi khi thêm dữ liệu vào bảng banggia: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
