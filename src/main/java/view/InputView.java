package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Supplier;

public class InputView {
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    public static String inputParticipantNames() {
        try {
            return BUFFERED_READER.readLine();
        } catch (IOException ioException) {
            OutputView.println(ioException.getMessage());
            return inputParticipantNames();
        }
    }

    public static <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException illegalArgumentException) {
            OutputView.println(illegalArgumentException.getMessage());
            return repeat(supplier);
        }
    }
}
