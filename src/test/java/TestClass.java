import com.github.auribuo.input.BetterInput.BetterInput;

import java.io.IOException;

public class TestClass {
    public static void main(String[] args) throws IOException {
        var inp = BetterInput.instance();
        System.out.println(inp.readLine().asString());
    }
}
