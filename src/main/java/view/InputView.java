package view;

import domain.game.AddCardCommand;

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
    private static final String NEW_LINE = System.lineSeparator();
    
    private InputView() {
        throw new IllegalArgumentException("인스턴스를 생성할 수 없는 클래스입니다.");
    }
    
    public static String inputParticipantNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
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

    private static void validateInputParticipantNamesFormat(String inputParticipantNames) {
        Matcher matcher = INPUT_PARTICIPANT_NAMES_FORMAT.matcher(inputParticipantNames);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("참가자 이름 입력 형식이 잘못 되었습니다. 다시 입력해주세요.");
        }
    }

    public static AddCardCommand inputAddCardCommand(String playerName) {
        System.out.printf(NEW_LINE + "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + NEW_LINE, playerName);
        try {
            String inputAddCardCommand = BUFFERED_READER.readLine();
            validateNullOrBlank(inputAddCardCommand);
            return AddCardCommand.valueOfCommand(inputAddCardCommand);
        } catch (IOException ioException) {
            OutputView.println(ioException.getMessage());
            return inputAddCardCommand(playerName);
        }
    }

    public static double inputBetAmount(String playerName) {
        System.out.printf(NEW_LINE + "%s의 배팅 금액은?" + NEW_LINE, playerName);
        try {
            String betAmount = BUFFERED_READER.readLine();
            validateNullOrBlank(betAmount);
            return Integer.parseInt(betAmount);
        } catch (IOException | NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }
    
    private static void validateNullOrBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("Null 또는 빈 문자열을 입력할 수 없습니다.");
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
