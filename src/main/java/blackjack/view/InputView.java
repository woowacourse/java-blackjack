package blackjack.view;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_USERNAME_GUIDE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_MORE_CARD_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + lineSeparator();
    private static final String REGEX = ", |,";

    public static List<String> inputUsersName() {
        System.out.println(INPUT_USERNAME_GUIDE);
        String userNameInput = scanner.nextLine();
        String[] userNames = userNameInput.split(REGEX);
        return Arrays.stream(userNames)
                .collect(Collectors.toList());
    }

    public static boolean inputMoreCard(String userName) {
        System.out.printf(INPUT_MORE_CARD_FORMAT, userName);
        String agreementInput = scanner.nextLine();
        InputValidator.validateAgreement(agreementInput);
        return Agreement.isAgree(agreementInput);
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
        return InputValidator.validateInteger(bettingPrice);
    }
}
