package comp.Rmi.rmi;

import comp.Rmi.model.CTHDDetailsDTO;
import comp.Rmi.model.HoaDon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface HoaDonService extends Remote {
    public List<HoaDon> showAllHoaDon() throws RemoteException;

    public HoaDon findHoaDonByID(int hoadonID) throws RemoteException;

    public List<CTHDDetailsDTO> getHoaDonDetailsByNhanVienID(int nhanVienID) throws RemoteException;
}
