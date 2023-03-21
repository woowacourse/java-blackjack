package view;

import domain.player.Bet;
import domain.player.Name;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class InputView {
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    public static List<Name> inputParticipantNames() {
        try {
            String input = BUFFERED_READER.readLine();
            List<String> names = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .collect(Collectors.toUnmodifiableList());
            return Name.of(names);
        } catch (IOException ioException) {
            OutputView.println(ioException.getMessage());
            return inputParticipantNames();
        }
    }

    public static Command inputAddCardCommand() {
        try {
            String input = BUFFERED_READER.readLine();
            return Command.from(input);
        } catch (IOException ioException) {
            OutputView.println(ioException.getMessage());
            return inputAddCardCommand();
        }
    }

    public static Bet inputBet() {
        try {
            String input = BUFFERED_READER.readLine();
            return Bet.from(Integer.parseInt(input));
        } catch (IOException ioException) {
            OutputView.println(ioException.getMessage());
            return inputBet();
        } catch (NumberFormatException formatException) {
            OutputView.println("정수를 입력해주세요.");
            return inputBet();
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
