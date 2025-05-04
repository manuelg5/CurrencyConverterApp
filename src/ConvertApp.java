import javax.swing.SwingUtilities;

public class ConvertApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConvertFrame frame = new ConvertFrame();
            frame.setVisible(true);
        });
    }
}
