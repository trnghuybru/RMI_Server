package comp.trainticketserver.rmi;

import comp.trainticketserver.model.Tuyen;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TuyenService extends Remote {
    List<Tuyen> getAllTuyen() throws RemoteException;
}
