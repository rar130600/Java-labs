import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter("content.properties"))) {
            Properties properties = new Properties();
            properties.setProperty("email", "mail@mail.com");
            properties.setProperty("first_name", "Alex");
            properties.setProperty("last_name", "Rakhmatullin");
            properties.store(out, "test properties");
        } catch (FileNotFoundException except) {
            System.err.println("File is not found: " + except.getMessage());
        } catch (IOException except) {
            except.printStackTrace();
        }

        try {
            PropertiesReader propertiesReader = new PropertiesReader("content.properties");

            System.out.println("Get properties:");
            System.out.println("Email: " + propertiesReader.getProperty("email"));
            System.out.println("First name: " + propertiesReader.getProperty("first_name"));
            System.out.println("Last name: " + propertiesReader.getProperty("last_name"));
            System.out.println();

            System.out.println("Output by function:");
            propertiesReader.list(System.out);
            System.out.println();

            System.out.println("Output by Map:");
            Map<String, String> map = propertiesReader.getAsMap();
            map.forEach((key, value) -> System.out.println("Key: " + key + " Value: " + value));
            System.out.println();

            System.out.println("Set new property:\n\"Key: login Value: programmer\"");
            propertiesReader.setProperty("login", "programmer");
            System.out.println();

            System.out.println("Output properties:");
            propertiesReader.list(System.out);

        } catch (FileNotFoundException except) {
            System.err.println("File is not found: " + except.getMessage());
        } catch (IOException except) {
            except.printStackTrace();
        }
    }
}
