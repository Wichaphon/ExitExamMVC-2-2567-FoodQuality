// model/FreshFood.java
package model;

import java.util.Date;

public class FreshFood extends FoodItem {
    public FreshFood(String id, String expiryDate) {
        super(id, "อาหารสด", expiryDate);
    }
    //ตรวจอาหารสด
    @Override
    public boolean isExpired() {
        Date expiry = parseDate();
        return expiry != null && new Date().after(expiry);
    }
}