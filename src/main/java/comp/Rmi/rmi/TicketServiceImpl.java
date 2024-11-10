package comp.Rmi.rmi;

import comp.trainticketserver.DAO.TicketDAO;
import comp.trainticketserver.DAO.TrainDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TicketServiceImpl extends UnicastRemoteObject implements TicketService {

    private TicketDAO ticketDAO;

    protected TicketServiceImpl() throws RemoteException {
        ticketDAO = new TicketDAO();
    }

    @Override
    public boolean bookTicket(int gheID, int tauID, int gaDi, int gaDen, String tenKH, String diaChi, String sdt, float giaTien, int nhanVienID) throws RemoteException {
        return ticketDAO.bookTicket(gheID, tauID, gaDi, gaDen, tenKH, diaChi, sdt, giaTien, nhanVienID);
    }

    @Override
    public boolean cancelTicket(int hoaDonID, int gheID) throws RemoteException {
        return ticketDAO.cancelTicket(hoaDonID,gheID);
    }


}
