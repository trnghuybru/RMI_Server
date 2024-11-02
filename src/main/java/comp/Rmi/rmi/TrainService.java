package comp.trainticketserver.rmi;

import comp.trainticketserver.model.Train;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TrainService extends Remote {
    List<Train> searchTrainsByTuyenId(int tuyenId, String ngayKhoiHanh) throws RemoteException;
}
