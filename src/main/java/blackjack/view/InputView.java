package blackjack.view;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class InputView {

    private static final String ERROR_NOT_Y_OR_N = "[ERROR] y 또는 n만 입력 가능합니다.";
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_USERNAME_GUIDE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_MORE_CARD_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + lineSeparator();
    private static final String INPUT_INVALID_ANSWER = "[ERROR] y 또는 n만 입력 가능합니다.";
    private static final String AGREE = "y";
    private static final String DISAGREE = "n";
    private static final String REGEX = ", |,";
    private static final String ERROR_INVALID_INTEGER = "[ERROR] 정수만 입력 가능합니다.";

    private enum Agreement {
        AGREE("y"),
        DISAGREE("n");

        private String name;

        Agreement(String name) {
            this.name = name;
        }

        public static boolean isAgree(String input) {
            Agreement agreement = Arrays.stream(values())
                    .filter(item -> item.check(input))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_Y_OR_N));

            return agreement == AGREE;
        }

        public boolean check(String name) {
            return this.name.equals(name);
        }

    }
    public static List<String> inputUsersName() {
        System.out.println(INPUT_USERNAME_GUIDE);
        return Arrays.stream(scanner.nextLine().split(REGEX)).collect(Collectors.toList());
    }

    public static boolean inputMoreCard(String userName) {
        System.out.printf(INPUT_MORE_CARD_FORMAT, userName);
        return Agreement.isAgree(scanner.nextLine());
    }

    public static Map<String, Integer> inputUserNameAndBettingPrice(List<String> userNames) {
        Map<String, Integer> bettingPriceByUserName = new HashMap<>();
        for (String userName : userNames) {
            bettingPriceByUserName.put(userName, getUserBettingPrice(userName));
        }
        return bettingPriceByUserName;
    }

    public static int getUserBettingPrice(String userName) {
        System.out.println(userName + "의 베팅 금액은?");
        String bettingPrice = scanner.nextLine();
        return toInteger(bettingPrice);
    }

    private static int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_INTEGER);
        }
    }
}
