// model/FoodItem.java
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class FoodItem {
    protected String id;
    protected String type;
    protected String expiryDate;

    public FoodItem(String id, String type, String expiryDate) {
        this.id = id;
        this.type = type;
        this.expiryDate = expiryDate;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public abstract boolean isExpired();

    protected Date parseDate() {
        try {
            return new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(expiryDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}