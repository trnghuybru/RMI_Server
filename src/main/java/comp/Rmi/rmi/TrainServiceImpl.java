package comp.trainticketserver.rmi;

import comp.trainticketserver.DAO.TrainDAO;
import comp.trainticketserver.model.Train;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class TrainServiceImpl extends UnicastRemoteObject implements TrainService {
    private TrainDAO trainDAO;

    public TrainServiceImpl() throws RemoteException {
        trainDAO = new TrainDAO();
    }

    @Override
    public List<Train> searchTrainsByTuyenId(int tuyenId, String ngayKhoiHanh) throws RemoteException {
        return trainDAO.searchTrainsByTuyenId(tuyenId, ngayKhoiHanh);
    }
}
