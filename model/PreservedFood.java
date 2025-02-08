// model/PreservedFood.java
package model;

import java.util.Calendar;
import java.util.Date;

public class PreservedFood extends FoodItem {
    public PreservedFood(String id, String expiryDate) {
        super(id, "อาหารดอง", expiryDate);
    }

    //ตรวจอาหารดอง
    @Override
    public boolean isExpired() {
        Date expiry = parseDate();
        if (expiry == null) return false;
        
        Calendar expiryCal = Calendar.getInstance();
        expiryCal.setTime(expiry);
        int expiryMonth = expiryCal.get(Calendar.MONTH) + 1; // ปรับให้เดือนอยู่ในช่วง 1-12
        int expiryYear = expiryCal.get(Calendar.YEAR);
        int expiryDay = expiryCal.get(Calendar.DAY_OF_MONTH);
        
        Calendar today = Calendar.getInstance();
        int currentMonth = today.get(Calendar.MONTH) + 1;
        int currentYear = today.get(Calendar.YEAR);
        int currentDay = today.get(Calendar.DAY_OF_MONTH);
        
        // ตรวจสอบว่าเดือนปัจจุบันเกินเดือนหมดอายุไหม
        if (currentYear > expiryYear || (currentYear == expiryYear && currentMonth > expiryMonth)) {
            return true; // หมดอายุถ้าเลยเดือนที่กำหนด
        }
        
        // ถ้าอยู่ในเดือนหมดอายุ ต้องตรวจสอบว่าวันปัจจุบันเลยวันหมดอายุหรือไม่
        return (currentYear == expiryYear && currentMonth == expiryMonth && currentDay > expiryDay);
    }
}
