import java.io.IOException;

public class ExceptionsDemo {
    public static void main(String[] args) {
        try {
            int today = 4;
            if (today == 4) {
                throw new IOException("for no reason :)");
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
