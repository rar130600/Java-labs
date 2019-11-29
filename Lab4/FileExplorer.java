import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.function.Consumer;

public class FileExplorer {
    private Path currentPath = Paths.get("").toAbsolutePath();
    private final HashMap<String, Consumer<String>> commandMap = new HashMap<>() {{
        put("cd", FileExplorer.this::goToDirectory);
        put("ls", FileExplorer.this::listDirectory);
        put("touch", FileExplorer.this::createFile);
        put("mkdir", FileExplorer.this::createDirectory);
        put("rm", FileExplorer.this::delete);
        put("cat", FileExplorer.this::showFile);
        put("addToFile", FileExplorer.this::appendToFile);
    }};

    public void runCommand (String line) {
        String[] strs = parseLine(line);
        String cmd = strs[0];
        String otherPart = strs[1];
        if ((!commandMap.containsKey(cmd) || otherPart.equals("")) && !cmd.equals("ls"))
        {
            System.err.println("Invalid command");
            return;
        }
        commandMap.get(cmd).accept(otherPart);
    }

    private String[] parseLine (String line) {
        String[] strs = new String[2];

        line = line.trim();
        int index = line.indexOf(" ");
        if (index == -1 || line.isBlank()) {
            strs[0] = line;
            strs[1] = "";
            return strs;
        }
        strs[0] = line.substring(0, index);
        line = line.substring(index).trim();
        strs[1] = line;

        return  strs;
    }

    public Path getPath() {
        return currentPath;
    }

    public void helpPrint () {
        System.out.println("cd <dir_name> - go to directory");
        System.out.println("ls - show files in current directory");
        System.out.println("touch <file_name> - create a new file");
        System.out.println("mkdir <dir_name> - create a new directory");
        System.out.println("rm <file_name> or <dir_name> - remove file or directory");
        System.out.println("cat <file_name> - show file");
        System.out.println("addToFile <file_name> - append to file");
    }

    private void goToDirectory (String path) {
        try {
            Path correctPath = extractPath(path);
            if (!Files.isDirectory(correctPath)) {
                System.err.println("Is not a directory: " + correctPath);
                return;
            }
            currentPath = correctPath;
        } catch (InvalidPathException except) {
            System.err.println(except.getMessage());
        }
    }

    private void listDirectory (String path) {
        try {
            Path correctPath = extractPath(path);
            if (!Files.isDirectory(correctPath)) {
                System.err.println("Is not a directory: " + correctPath);
            }
            File[] directory = (new File(correctPath.toUri())).listFiles();
            if (directory != null) {
                for (File file : directory) {
                    System.out.println(file.getName());
                }
            }
        } catch (InvalidPathException except) {
            System.err.println(except.getMessage());
        }
    }

    private void createFile (String fileName) {
        Path path = currentPath.resolve(Paths.get(fileName)).normalize();
        if (!Files.isDirectory(path.getParent()) || Files.notExists(path.getParent())) {
            System.err.println("Invalid path: " + path.getParent());
        }

        try {
            File newFile = new File(path.toUri());
            if (!newFile.createNewFile()) {
                System.err.println("File with the same name already exists");
            }
        } catch (IOException except) {
            System.err.println("File creation error: " + except.getMessage());
        }
    }

    private void createDirectory (String dirName) {
        Path path = currentPath.resolve(Paths.get(dirName)).normalize();
        if (!Files.isDirectory(path.getParent()) || Files.notExists(path.getParent())) {
            System.err.println("Invalid path: " + path.getParent());
        }

        File newDir = new File(path.toUri());
        if (!newDir.mkdir()) {
            System.err.println("Directory with the same name already exists");
        }
    }

    private void delete (String name) {
        try {
            Path correctPath = extractPath(name);
            if (!Files.deleteIfExists(correctPath)) {
                System.err.println("Invalid name of file/directory");
            }
        } catch (InvalidPathException except) {
            System.err.println(except.getMessage());
        } catch (DirectoryNotEmptyException except) {
            System.err.println("The directory is not empty");
        } catch (IOException except) {
            System.err.println("File deletion error: " + except.getMessage());
        }
    }

    private void showFile (String fileName) {
        try {
            File showingFile = new File(extractPath(fileName).toUri());
            if (!showingFile.isFile()) {
                System.err.println("Is not a file");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(showingFile));
            String fileLine;
            while ((fileLine = br.readLine()) != null) {
                 System.out.println(fileLine);
            }
            br.close();
        } catch (IOException except) {
            System.err.println("File reading error: " + except.getMessage());
        } catch (InvalidPathException except) {
            System.err.println(except.getMessage());
        }
    }

    private void appendToFile (String fileName) {
        try {
            File appendingFile = new File(extractPath(fileName).toUri());
            if (!appendingFile.isFile()) {
                System.err.println("Is not a file");
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new FileWriter(appendingFile, true));
            String fileLine;
            while (!((fileLine = br.readLine()).equals("q!"))) {
                bw.write(fileLine + "\n");
            }
            bw.close();
        } catch (IOException except) {
            System.err.println("File writing error: " + except.getMessage());
        } catch (InvalidPathException except) {
            System.err.println(except.getMessage());
        }
    }

    private Path extractPath (String str) throws InvalidPathException {
        Path path = currentPath.resolve(Paths.get(str)).normalize();
        if (Files.notExists(path)) {
            throw new InvalidPathException(path.toString(), "Invalid path");
        }
        return path;
    }
}
