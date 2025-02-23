import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertiesUtil {
    
    // 🔹 Đọc file properties từ classpath (ResourceBundle)
    public static String getPropertyFromClasspath(String fileName, String key) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(fileName);
            return bundle.getString(key);
        } catch (Exception e) {
            System.err.println("Lỗi khi đọc ResourceBundle: " + e.getMessage());
            return null;
        }
    }

    // 🔹 Đọc file properties từ đường dẫn tùy chỉnh (FileInputStream)
    public static String getPropertyFromFile(String filePath, String key) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
            return properties.getProperty(key);
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file properties: " + e.getMessage());
            return null;
        }
    }

    // 🔹 Đọc file properties với UTF-8 (hỗ trợ tiếng Việt, ký tự đặc biệt)
    public static String getPropertyUTF8(String filePath, String key) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(new java.io.InputStreamReader(input, StandardCharsets.UTF_8));
            return properties.getProperty(key);
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file properties UTF-8: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // 🏆 Đọc từ classpath (src/resources)
        System.out.println("🔹 Từ classpath: " + getPropertyFromClasspath("config", "db.url"));

        // 🏆 Đọc từ file ngoài classpath
        System.out.println("🔹 Từ file ngoài classpath: " + getPropertyFromFile("C:/config/app.properties", "db.url"));

        // 🏆 Đọc từ file ngoài classpath hỗ trợ UTF-8
        System.out.println("🔹 Từ file UTF-8: " + getPropertyUTF8("C:/config/app_utf8.properties", "app.name"));
    }
}
