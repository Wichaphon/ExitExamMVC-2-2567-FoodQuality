// controller/FoodController.java
package controller;

import model.*;
import java.io.IOException;
import java.util.List;

public class FoodController {
    /**
     * ดึงข้อมูลอาหารทั้งหมดจากไฟล์ CSV
     * return รายการอาหารทั้งหมด
     */
    public static List<FoodItem> getAllFoodItems() {
        try {
            return CSVHandler.readFoodItems();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //ตรวจสอบว่าสินค้ามีอยู่ในฐานข้อมูลไหม
     
    public static boolean isFoodIdValid(String foodId) {
        try {
            return CSVHandler.checkFoodIdExists(foodId);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    //ตรวจสอบว่าอาหารหมดอายุหรือยัง
     
    public static boolean checkIfExpired(FoodItem foodItem) {
        return foodItem.isExpired();
    }

    /**
     * ตรวจสอบค่าที่ผู้ใช้กรอกเข้ามา ว่าเป็นรหัสที่ถูกต้องหรือไม่
     * return ข้อความแจ้งเตือนข้อผิดพลาด หรือ "OK" ถ้าผ่าน
     */
    public static String validateFoodId(String foodId) {
        if (!foodId.matches("[1-9][0-9]{5}")) {
            return "ERROR: กรุณากรอกรหัสอาหารที่เป็นตัวเลข 6 หลัก และไม่ขึ้นต้นด้วย 0";
        }
        try {
            if (!CSVHandler.checkFoodIdExists(foodId)) {
                return "ERROR: ไม่พบรหัสอาหารนี้ในระบบ";
            }
        } catch (IOException e) {
            return "ERROR: ไม่สามารถเข้าถึงฐานข้อมูลได้";
        }
        return "OK";
    }

    /**
     * ตรวจสอบข้อมูลอาหาร และคืนวันหมดอายุ
     * return ข้อมูลอาหารและสถานะหมดอายุ
     */
    public static String getFoodStatus(String foodId) {
        List<FoodItem> foodItems;
        try {
            foodItems = CSVHandler.readFoodItems();
        } catch (IOException e) {
            return "ERROR: ไม่สามารถโหลดข้อมูลอาหารได้";
        }

        for (FoodItem item : foodItems) {
            if (item.getId().equals(foodId)) {
                boolean expired = item.isExpired();
                String result = "รหัส: " + item.getId() + "\nประเภท: " + item.getType() + "\nวันหมดอายุ: " + item.getExpiryDate() + "\nสถานะ: " + (expired ? "หมดอายุแล้ว" : "ยังใช้ได้");
                System.out.println(result); //Debugging
                return result;
            }
        }
        return "ERROR: ไม่พบข้อมูลอาหารนี้";
    }
}
