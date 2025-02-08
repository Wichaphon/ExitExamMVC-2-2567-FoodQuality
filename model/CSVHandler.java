// model/CSVHandler.java
package model;

import java.io.*;
import java.util.*;

public class CSVHandler {
    private static final String FILE_NAME = "resources/food_data.csv"; // เปลี่ยน path ไปที่โฟลเดอร์ resources
    
    /**
     * อ่านข้อมูลจากไฟล์ CSV และแปลงเป็นรายการอาหาร
     * return รายการของ FoodItem ที่ถูกโหลดจากไฟล์
     * throws IOException ถ้ามีข้อผิดพลาดตอนอ่านไฟล์
     */
    public static List<FoodItem> readFoodItems() throws IOException {
        List<FoodItem> foodItems = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), "UTF-8"));
        String line;
        boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (firstLine) { //ข้ามบรรทัดแรกเพราะเป็น Header
                firstLine = false;
                continue;
            }
            String[] data = line.split(",");
            String id = data[0].trim();
            String type = data[1].trim(); // ตัดช่องว่าง
            String expiryDate = data[2].trim();
            
            System.out.println("DEBUG: อ่านประเภทอาหาร -> " + type); //Debug
            
            // ตรวจสอบประเภทอาหาร
            switch (type) {
                case "อาหารสด":
                    foodItems.add(new FreshFood(id, expiryDate));
                    break;
                case "อาหารดอง":
                    foodItems.add(new PreservedFood(id, expiryDate));
                    break;
                case "อาหารกระป๋อง":
                    foodItems.add(new CannedFood(id, expiryDate));
                    break;
                default:
                    System.err.println("ERROR: พบประเภทอาหารที่ไม่ถูกต้อง -> " + type);
                    throw new IllegalArgumentException("ประเภทอาหารไม่ถูกต้องในไฟล์ CSV: " + type);
            }
        }
        reader.close();
        return foodItems;
    }

    /**
     * ตรวจสอบว่ารหัสอาหารมีอยู่ในไฟล์ CSV ไหม
     * foodId-รหัสอาหารที่ต้องการตรวจสอบ
     * return true ถ้าพบรหัสในฐานข้อมูล false ถ้าไม่พบ
     * throws IOException ถ้ามีข้อผิดพลาดในการอ่านไฟล์
     */
    public static boolean checkFoodIdExists(String foodId)throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        boolean firstLine = true;
        while ((line = reader.readLine()) !=null) {
            if (firstLine) { //ข้ามบรรทัดแรกเพราะเป็น Header
                firstLine = false;
                continue;
            }
            
            String[] data = line.split(",");
            if (data[0].trim().equals(foodId)) {
                reader.close();
                return true; //เจอรหัสอาหารในฐานข้อมูล
            }
        }
        reader.close();
        return false; //ไม่เจอรหัสอาหารในฐานข้อมูล
    }
}
