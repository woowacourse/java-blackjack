package blackjack.view;

import blackjack.domain.money.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_NAME_MSG = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_ANSWER_MSG = "%s는 한 장의 카드를 더 받으시겠습니까?";
    private static final String INPUT_MONEY_MSG = "%s의 배팅 금액은?";

    private static final String YES_OR_NO_REGEX = "^[YNyn]$";

    public static List<String> receiveNames() {
        return Arrays.stream(receiveInput(INPUT_NAME_MSG).split(","))
                .map(String::trim)
                .map(InputView::validateName)
                .collect(Collectors.toList());
    }

    public static List<Money> receiveMoney(List<String> names) {
        List<Money> money = new ArrayList<>();
        for (String name : names) {
            System.out.printf((INPUT_MONEY_MSG) + "%n", name);
            money.add(new Money(SCANNER.nextLine().trim()));
        }
        return money;
    }

    public static boolean receiveAnswer(String name) {
        String answer = receiveInput(String.format(INPUT_ANSWER_MSG, name));
        validateAnswer(answer);
        return "y".equalsIgnoreCase(answer);
    }

    private static String receiveInput(String message) {
        System.out.println(message);
        return SCANNER.nextLine().trim();
    }

    private static String validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 한 글자 이상이어야 합니다.");
        }
        return name;
    }

    private static void validateAnswer(String answer) {
        if (!answer.matches(YES_OR_NO_REGEX)) {
            throw new IllegalArgumentException("[ERROR] y/n만 입력 가능합니다.");
        }
    }
}
