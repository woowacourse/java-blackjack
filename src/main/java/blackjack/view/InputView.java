package blackjack.view;

import blackjack.domain.participant.attribute.Name;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String DELIMITER = ",";
    private static final String ENTER_NAMES_MSG = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String SELECT_YES_OR_NO_MSG = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String ENTER_BETTING_MONEY_MSG = "%s의 배팅 금액은?";
    private static final String INVALID_MONEY_INPUT_TYPE_ERR_MSG = "배팅 금액에는 정수만 들어올 수 있습니다.";

    public static String enterGame() {
        System.out.println("실행할 게임을 선택해주세요. 기본 블랙잭 : 1, 배팅 블랙잭: 2");
        return scanner.nextLine().trim();
    }

    public static List<String> enterNames() {
        System.out.println(ENTER_NAMES_MSG);
        String input = scanner.nextLine();
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static List<Integer> enterBettingMoney(List<Name> names) {
        List<Integer> moneys = new ArrayList<>();
        for (Name name : names) {
            System.out.println(String.format(ENTER_BETTING_MONEY_MSG, name.getName()));
            int money = convertToNumber(scanner.nextLine());
            moneys.add(money);
        }
        return moneys;
    }

    private static int convertToNumber(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(INVALID_MONEY_INPUT_TYPE_ERR_MSG);
        }
    }

    public static String readYesOrNo(String name) {
        System.out.println(String.format(SELECT_YES_OR_NO_MSG, name));
        return (scanner.nextLine()).trim();
    }
}
