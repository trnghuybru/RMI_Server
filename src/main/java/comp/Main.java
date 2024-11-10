package comp;

import comp.Rmi.rmi.*;
import comp.trainticketserver.DAO.TicketDAO;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

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

            Naming.rebind("rmi://localhost/TrainService", trainService);
            Naming.rebind("rmi://localhost:1099/CarriageService", carriageService);
            Naming.rebind("rmi://localhost:1099/PriceService", priceService);
            Naming.rebind("rmi://localhost:1099/SeatService", seatService);
            Naming.rebind("rmi://localhost:1099/StationService", stationService);


            System.out.println("Service is running...");

            // Khởi tạo đối tượng TicketDAO
            TicketDAO ticketDAO = new TicketDAO();

            // Thiết lập các tham số cần thiết cho việc đặt vé
            int gheID = 1; // ID ghế
            int tauID = 1; // ID tàu
            int gaDi = 1;  // ID ga đi
            int gaDen = 2; // ID ga đến
            String tenKH = "Nguyen Van A"; // Tên khách hàng
            String diaChi = "123 Nguyen Trai, TP.HCM"; // Địa chỉ khách hàng
            String sdt = "0987654321"; // Số điện thoại khách hàng
            float giaTien = 200000; // Giá tiền vé
            int nhanVienID = 1; // ID nhân viên

            // Gọi phương thức bookTicket để đặt vé
            boolean isBooked = ticketDAO.bookTicket(gheID, tauID, gaDi, gaDen, tenKH, diaChi, sdt, giaTien, nhanVienID);

            // Kiểm tra kết quả đặt vé
            if (isBooked) {
                System.out.println("Đặt vé thành công!");
            } else {
                System.out.println("Đặt vé không thành công.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
