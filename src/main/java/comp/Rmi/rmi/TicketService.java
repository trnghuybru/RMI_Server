package comp.Rmi.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicketService extends Remote {
    public boolean bookTicket(int gheID, int tauID, int gaDi, int gaDen, String tenKH, String diaChi, String sdt, float giaTien, int nhanVienID) throws RemoteException;
    public boolean cancelTicket(int hoaDonID, int gheID) throws RemoteException;
}
