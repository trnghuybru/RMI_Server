package comp.trainticketserver.DAO;

import comp.Rmi.model.Carriage;
import comp.Rmi.model.Price;
import comp.Rmi.model.Train;
import comp.Rmi.model.TrainCarriage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToaDAO {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection(); // Phương thức để lấy kết nối cơ sở dữ liệu
    }

    public List<TrainCarriage> getCarriageByTrainID(int trainID, int gaDi, int gaDen) {
        List<TrainCarriage> trainCarriages = new ArrayList<>();
        PriceDAO priceDAO = new PriceDAO();
        List<Price> prices = priceDAO.getUniquePrices(trainID, gaDi, gaDen);

        // Kiểm tra nếu không có giá trị nào trong danh sách unique prices
        if (prices.isEmpty()) {
            return trainCarriages; // Trả về danh sách trống nếu không có giá nào phù hợp
        }


        // Tạo chuỗi truy vấn SQL
        String sql = "SELECT DISTINCT tt.TauToaID, t.TauID, t.TenTau, c.ToaID, c.TenToa " +
                "FROM tautoa tt " +
                "JOIN tau t ON tt.TauID = t.TauID " +
                "JOIN toa c ON tt.ToaID = c.ToaID " +
                "JOIN ghe g ON c.ToaID = g.ToaID " +
                "WHERE tt.TauID = ? AND g.GheID IN (" +
                prices.stream().map(price -> "?").collect(Collectors.joining(", ")) + ")";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Đặt giá trị cho tham số `trainID`
            ps.setInt(1, trainID);

            // Đặt các giá trị cho danh sách GheID từ `prices`
            for (int i = 0; i < prices.size(); i++) {
                ps.setInt(i + 2, prices.get(i).getGheID()); // Lấy GheID từ đối tượng Price
            }

            // Thực thi truy vấn và xử lý kết quả
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TrainCarriage trainCarriage = new TrainCarriage();

                    // Thiết lập TrainCarriage
                    trainCarriage.setTauToaID(rs.getInt("TauToaID"));

                    // Thiết lập Train
                    Train train = new Train();
                    train.setTauID(rs.getInt("TauID"));
                    train.setTenTau(rs.getString("TenTau"));
                    trainCarriage.setTrain(train);

                    // Thiết lập Carriage
                    Carriage carriage = new Carriage();
                    carriage.setToaID(rs.getInt("ToaID"));
                    carriage.setTenToa(rs.getString("TenToa"));
                    trainCarriage.setCarriage(carriage);

                    // Thêm TrainCarriage vào danh sách kết quả
                    trainCarriages.add(trainCarriage);
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trainCarriages;
    }

}
