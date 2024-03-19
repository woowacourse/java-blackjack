package blackjack.view;

import blackjack.domain.gamer.Name;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String MORE_CARD = "y";
    private static final String NOT_MORE_CARD = "n";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final List<String> playerNames = List.of(SCANNER.nextLine().split(","));
        System.out.println();
        return playerNames;
    }

    public static double readBettingMoney(final Name playerName) {
        System.out.println(playerName.value() + "의 배팅 금액은?");
        final double bettingMoney = readDouble();
        System.out.println();
        return bettingMoney;
    }

    private static double readDouble() {
        try {
            return Double.parseDouble(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalStateException("숫자만 입력해주세요.");
        }
    }

    public static boolean readDoesWantHit(final Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)" + System.lineSeparator(),
                name.value(), MORE_CARD, NOT_MORE_CARD);
        String input = SCANNER.nextLine();
        return input.equals(MORE_CARD);
    }
}
