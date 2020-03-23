package blackjack.view;

import blackjack.domain.user.Player;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NEW_LINE = System.lineSeparator();

    public static Map<String, String> inputPlayerProperties() {
        return Arrays.stream(inputPlayerNames().split(","))
                .map(String::trim)
                .collect(Collectors.toMap(Function.identity(), name -> inputBettingMoney(name)));
    }

    private static String inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return SCANNER.nextLine();
    }

    private static String inputBettingMoney(String name) {
        System.out.printf("%s의 배팅 금액은?" + NEW_LINE, name);
        return SCANNER.nextLine();
    }

    public static String inputToHitOrStay(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + NEW_LINE, player.getName());
        return SCANNER.nextLine();
    }
}
