import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {
    private Map<String, String> content = new HashMap<>();

    public PropertiesReader(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            Properties properties = new Properties();
            properties.load(br);
            properties.forEach((key, value) -> content.put(key.toString(), value.toString()));
        }
    }

    public String getProperty(String key) {
        return content.get(key);
    }

    public void setProperty(String key, String value) {
        content.put(key, value);
    }

    public void list(PrintStream out) {
        content.forEach((key, value) -> out.println(key + "=" + value));
    }

    public void list(PrintWriter out) {
        content.forEach((key, value) -> out.println(key + "=" + value));
    }

    public Map<String, String> getAsMap() {
        return content;
    }
}
