import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        FileExplorer fe = new FileExplorer();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Type \"help\" for more information about commands");

            String line;
            while(true) {
                System.out.print(fe.getPath() + "$: ");
                line = br.readLine().trim();
                if (line.equals("exit")) {
                    break;
                }
                if (line.equals("help")) {
                    fe.helpPrint();
                    continue;
                }
                fe.runCommand(line);
            }
        } catch (IOException except) {
            System.err.println("Input/Output error: " + except.getMessage());
            except.printStackTrace();
        }
    }
}
