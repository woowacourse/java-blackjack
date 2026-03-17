package view;

import exception.BlackjackException;
import exception.ExceptionMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private InputView() {
    }

    public static String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new BlackjackException(ExceptionMessage.INPUT_ERROR);
        }
    }
}
