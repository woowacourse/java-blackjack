package blackjack.view;

import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String USER_NAME_EXCEPTION_MESSAGE = "유저의 이름이 잘못되었습니다.";
    private static final String GAME_START_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String DRAW_CARD_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String ANSWER_EXCEPTION_MESSAGE = "잘못된 입력입니다.";
    private static final String BETTING_MONEY_QUESTION_MESSAGE = "의 배팅금액은?";
    private static final String NOT_NUMBER_EXCEPTION = "숫자를 입력해주세요.";

    private static Scanner scanner = new Scanner(System.in);

    public static List<String> inputUsers() {
        System.out.println(GAME_START_MESSAGE);
        String users = scanner.nextLine();
        return validateUsersName(users);
    }

    private static List<String> validateUsersName(final String users) {
        String[] usersName = users.split(",");
        if (Arrays.stream(usersName)
                .anyMatch(String::isBlank)) {
            throw new IllegalArgumentException(USER_NAME_EXCEPTION_MESSAGE);
        }
        return Arrays.stream(usersName)
                .collect(Collectors.toList());
    }

    public static boolean inputDrawCardAnswer(final Player user) {
        System.out.println();
        System.out.println(user.getName() + DRAW_CARD_MESSAGE);
        String answer = scanner.nextLine();
        return validateDrawCardAnswer(answer);
    }

    private static boolean validateDrawCardAnswer(final String answer) {
        if (answer.equals(YES) || answer.equals(NO)) {
            return answer.equals(YES);
        }
        throw new IllegalArgumentException(ANSWER_EXCEPTION_MESSAGE);
    }

    public static int inputBettingMoney(User user) {
        System.out.println();
        System.out.println(user.getName() + BETTING_MONEY_QUESTION_MESSAGE);
        String money = scanner.nextLine();
        return validateNumber(money);
    }

    private static int validateNumber(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_EXCEPTION);
        }
    }
}
