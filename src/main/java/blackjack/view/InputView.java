package blackjack.view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String REQUEST_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DELIMITER = ",";
    private static final int SPLIT_LIMIT = -1;
    private static final String INPUT_BLANK = " ";
    private static final String INPUT_NOT_BLANK = "";
    private static final String REQUEST_PLAYER_ANSWER_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String REQUEST_PLAYER_BETTING_MESSAGE = "%s의 배팅 금액은?\n";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> requestPlayerName() {
        System.out.println(REQUEST_PLAYER_NAME_MESSAGE);

        return Arrays.stream(scanner.nextLine().split(DELIMITER, SPLIT_LIMIT))
                .map(InputView::removeBlank)
                .collect(toList());
    }

    private static String removeBlank(final String name) {
        return name.replaceAll(INPUT_BLANK, INPUT_NOT_BLANK);
    }

    public static int requestBettingMoney(final String name) {
        System.out.printf(REQUEST_PLAYER_BETTING_MESSAGE, name);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException ("[ERROR] 베팅금은 숫자만 입력 가능합니다.");
        }
    }

    public static String requestAnswer(final String name) {
        System.out.printf(REQUEST_PLAYER_ANSWER_MESSAGE, name);
        return scanner.nextLine();
    }
}
