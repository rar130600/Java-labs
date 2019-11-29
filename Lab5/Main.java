import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileWriter("content.properties"))) {
            Properties propertiesCreate = new Properties();
            propertiesCreate.setProperty("email", "mail@mail.com");
            propertiesCreate.setProperty("first_name", "Alex");
            propertiesCreate.setProperty("last_name", "Rakhmatullin");
            propertiesCreate.store(out, "test properties");
        } catch (FileNotFoundException except) {
            System.err.println("File is not found: " + except.getMessage());
        } catch (IOException except) {
            except.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("content.properties"))){
            Properties propertiesLoad = new Properties();
            propertiesLoad.load(br);
            propertiesLoad.list(System.out);
        } catch (FileNotFoundException except) {
            System.err.println("File is not found: " + except.getMessage());
        } catch (IOException except) {
            except.printStackTrace();
        }
    }
}
