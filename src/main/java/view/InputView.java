package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
    private static final Pattern INPUT_PARTICIPANT_NAMES_FORMAT = Pattern.compile("([가-힣|a-zA-Z0-9]+)(,[가-힣|a-zA-Z0-9]+)*");

    public static String inputParticipantNames() {
        try {
            String inputParticipantNames = BUFFERED_READER.readLine();
            validateInputParticipantNames(inputParticipantNames);
            return inputParticipantNames;
        } catch (IOException ioException) {
            OutputView.println(ioException.getMessage());
            return inputParticipantNames();
        }
    }

    private static void validateInputParticipantNames(String inputParticipantNames) {
        validateNullOrBlank(inputParticipantNames);
        validateInputParticipantNamesFormat(inputParticipantNames);
    }

    private static void validateNullOrBlank(String inputParticipantNames) {
        if (Objects.isNull(inputParticipantNames) || inputParticipantNames.isBlank()) {
            throw new IllegalArgumentException("Null 또는 빈 문자열을 입력할 수 없습니다.");
        }
    }

    private static void validateInputParticipantNamesFormat(String inputParticipantNames) {
        Matcher matcher = INPUT_PARTICIPANT_NAMES_FORMAT.matcher(inputParticipantNames);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("참가자 이름 입력 형식이 잘못 되었습니다. 다시 입력해주세요.");
        }
    }

    public static String inputAddCardCommand() {
        try {
            return BUFFERED_READER.readLine();
        } catch (IOException ioException) {
            OutputView.println(ioException.getMessage());
            return inputAddCardCommand();
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
