package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {

    private static final String HIT_OR_STAY_REQUEST_MESSAGE = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String ASK_INPUT_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    private static final String ASK_INPUT_BET_AMOUNT = "%s의 배팅 금액은?\n";
    private static final String NATURAL_NUMBER_REGEX = "^[1-9]\\d*$";
    private static final String HIT_REQUEST = "y";
    private static final String STAY_REQUEST = "n";
    private static final String DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> requestNames() {
        askInputNames();
        return Arrays.asList(scanner.nextLine().split(DELIMITER, -1));
    }

    private static void askInputNames() {
        System.out.println(ASK_INPUT_NAMES_MESSAGE);
    }

    public static int requestBetAmount(String name) {
        askBetAmount(name);
        String betAmount = scanner.nextLine();
        validateNaturalNumber(betAmount);
        return Integer.parseInt(betAmount);
    }

    private static void askBetAmount(String name) {
        System.out.printf(ASK_INPUT_BET_AMOUNT, name);
    }

    private static void validateNaturalNumber(String betAmount) {
        Pattern pattern = Pattern.compile(NATURAL_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(betAmount);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NATURAL_NUMBER.getMessage());
        }
    }

    public static String requestDrawingCard(String name) {
        askDrawingCard(name);
        String drawingCardRequest = scanner.nextLine();
        validateDrawingCardRequest(drawingCardRequest);
        return drawingCardRequest;
    }

    private static void askDrawingCard(String name) {
        System.out.printf(HIT_OR_STAY_REQUEST_MESSAGE, name);
    }

    private static void validateDrawingCardRequest(String drawingCardRequest) {
        if (!drawingCardRequest.equals(HIT_REQUEST) && !drawingCardRequest.equals(STAY_REQUEST)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DRAWING_CARD_REQUEST.getMessage());
        }
    }
}
