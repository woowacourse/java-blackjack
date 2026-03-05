package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String DELIMITER = ",";

    public List<String> readNames() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        return Arrays.stream(br.readLine().split(DELIMITER)).toList();
    }
}
