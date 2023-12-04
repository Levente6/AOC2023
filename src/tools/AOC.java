package tools;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
public abstract class AOC {

    private Stream<String> stream = null;

    public AOC(String filename) {
        try {
            stream = Files.lines(Paths.get(filename));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        if(stream!=null) {
            stream.close();
        }
    }

    public Stream<String> getStream() {
        return stream;
    }

    public abstract void part1();
    public abstract void part2();
}
