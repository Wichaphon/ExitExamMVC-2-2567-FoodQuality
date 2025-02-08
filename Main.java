import view.FoodQualityGUI;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // เรียกใช้งาน GUI ของระบบตรวจสอบคุณภาพอาหาร
        SwingUtilities.invokeLater(() -> new FoodQualityGUI().setVisible(true));
    }
}
