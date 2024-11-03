package comp.Rmi.rmi;

import comp.Rmi.model.TrainCarriage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CarriageService extends Remote {
    public List<TrainCarriage> getCarriageByTrainID(int trainID,int gaDi, int gaDen) throws RemoteException;
}
