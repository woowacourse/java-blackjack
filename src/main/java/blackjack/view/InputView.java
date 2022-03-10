package blackjack.view;

import blackjack.domain.User;

import java.util.Scanner;

import static java.lang.System.lineSeparator;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_USERNAME_GUIDE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_MORE_CARD_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + lineSeparator();

    public static String[] inputUsersName() {
        System.out.println(INPUT_USERNAME_GUIDE);
        return scanner.nextLine().split(", |,");
    }

    public static boolean inputMoreCard(User user) {
        System.out.printf(INPUT_MORE_CARD_FORMAT, user.getName());
        return isContinue(scanner.nextLine());
    }

    private static boolean isContinue(String userInput) {
        if (userInput.equals("y")) {
            return true;
        }
        if (userInput.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("[ERROR] y 또는 n만 입력 가능합니다.");
    }
}
