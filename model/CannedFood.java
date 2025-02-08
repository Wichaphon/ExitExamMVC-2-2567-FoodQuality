// model/CannedFood.java
package model;

import java.util.Calendar;
import java.util.Date;

public class CannedFood extends FoodItem {
    public CannedFood(String id, String expiryDate) {
        super(id, "อาหารกระป๋อง", expiryDate);
    }

    //ตรวจอาหารกระป๋อง
    @Override
    public boolean isExpired() {
        Date expiry = parseDate();
        if (expiry == null) return false;
        
        Calendar expiryCal = Calendar.getInstance();
        expiryCal.setTime(expiry);
        expiryCal.set(Calendar.DAY_OF_MONTH, 31);
        expiryCal.set(Calendar.MONTH, Calendar.DECEMBER);
        expiryCal.add(Calendar.MONTH, 9);
        
        return new Date().after(expiryCal.getTime());
    }
}