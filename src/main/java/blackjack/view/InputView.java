package blackjack.view;

import blackjack.domain.Name;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputView {

    private static final String MORE_CARD = "y";
    private static final String NOT_MORE_CARD = "n";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Map<String, Double> readPlayerNameAndBettingMoney() {
        final List<String> playerNames = readPlayerNames();
        return createBettings(playerNames);
    }

    private static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final List<String> playerNames = List.of(SCANNER.nextLine().split(","));
        System.out.println();
        return playerNames;
    }

    private static Map<String, Double> createBettings(final List<String> playerNames) {
        final Map<String, Double> bettings = new LinkedHashMap<>();
        for (final String playerName : playerNames) {
            System.out.println(playerName + "의 배팅 금액은?");
            bettings.put(playerName, (double) readInteger());
            System.out.println();
        }
        return bettings;
    }

    private static int readInteger() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalStateException("숫자로 변환할 수 없는 입력입니다.");
        }
    }

    public static boolean readDoesWantHit(final Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)" + System.lineSeparator(),
                name.value(), MORE_CARD, NOT_MORE_CARD);
        String input = SCANNER.nextLine();
        return input.equals(MORE_CARD);
    }
}
