package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static String inputPlayerNames() throws IOException {
        return bufferedReader.readLine();
    }
}
