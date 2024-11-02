package com;

import com.trainticketserver.rmi.TuyenService;
import com.trainticketserver.rmi.TuyenServiceImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) {
        try {
            // Tạo registry RMI trên cổng 1099
            LocateRegistry.createRegistry(1099);

            // Đăng ký TuyenServiceImpl với RMI registry
            TuyenService tuyenService = new TuyenServiceImpl();
            Naming.rebind("rmi://localhost:1099/TuyenService", tuyenService);

            System.out.println("TuyenService is running...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
