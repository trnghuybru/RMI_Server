package comp.trainticketserver.DAO;

import java.sql.*;

public class TicketDAO {

    public boolean bookTicket(int gheID, int tauID, int gaDi, int gaDen, String tenKH, String diaChi, String sdt, float giaTien, int nhanVienID) {
        boolean isSuccess = false;
        Connection connection = null;

        try {
            // Kết nối với cơ sở dữ liệu
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false); // Bắt đầu giao dịch

            // 1. Khóa dòng ghế đang đặt bằng câu lệnh SELECT FOR UPDATE
            String sqlLockGhe = "SELECT GheID FROM ghe WHERE GheID = ? FOR UPDATE";
            try (PreparedStatement psLock = connection.prepareStatement(sqlLockGhe)) {
                psLock.setInt(1, gheID);
                ResultSet rs = psLock.executeQuery();

                if (!rs.next()) {
                    // Nếu không tìm thấy ghế, không thể đặt vé
                    connection.rollback();
                    return false;
                }

                // 2. Thêm hóa đơn vào bảng hoadon
                String sqlInsertHoaDon = "INSERT INTO hoadon (TenKH, DiaChi, SDT, NhanVienID, SoTien) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement psHoaDon = connection.prepareStatement(sqlInsertHoaDon, Statement.RETURN_GENERATED_KEYS)) {
                    psHoaDon.setString(1, tenKH);
                    psHoaDon.setString(2, diaChi);
                    psHoaDon.setString(3, sdt);
                    psHoaDon.setInt(4, nhanVienID);
                    psHoaDon.setFloat(5, giaTien);

                    int affectedRows = psHoaDon.executeUpdate();

                    // Kiểm tra nếu không thêm được hóa đơn
                    if (affectedRows == 0) {
                        connection.rollback();
                        return false;
                    }

                    // Lấy ID của hóa đơn vừa thêm
                    try (ResultSet rsHoaDon = psHoaDon.getGeneratedKeys()) {
                        int hoaDonID = 0;
                        if (rsHoaDon.next()) {
                            hoaDonID = rsHoaDon.getInt(1);

                            // 3. Thêm chi tiết hóa đơn vào bảng chitiethoadon
                            String sqlInsertCTHD = "INSERT INTO chitiethoadon (GheID, TauID, GaDi, GaDen, HoaDonID, NgayKhoiHanh) VALUES (?, ?, ?, ?, ?, ?)";
                            try (PreparedStatement psCTHD = connection.prepareStatement(sqlInsertCTHD)) {
                                psCTHD.setInt(1, gheID);
                                psCTHD.setInt(2, tauID);
                                psCTHD.setInt(3, gaDi);
                                psCTHD.setInt(4, gaDen);
                                psCTHD.setInt(5, hoaDonID);
                                psCTHD.setTimestamp(6, new Timestamp(System.currentTimeMillis())); // Ngày giờ hiện tại

                                affectedRows = psCTHD.executeUpdate();

                                // Kiểm tra nếu không thêm được chi tiết hóa đơn
                                if (affectedRows == 0) {
                                    connection.rollback();
                                    return false;
                                }
                            }
                        }
                    }
                }
            }

            // Nếu tất cả các thao tác thành công, commit giao dịch
            connection.commit();
            isSuccess = true;

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý rollback trong trường hợp có lỗi
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback nếu có lỗi
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            // Đảm bảo đóng kết nối
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isSuccess;
    }
}
