package blackjack.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> getPlayerNames() {
        try {
            return requestPlayerNames();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getPlayerNames();
        }
    }

    private static List<String> requestPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        final String text = scanner.nextLine();
        return Stream.of(text.split(",", -1))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static PlayerAnswer getHitOrStay(final String playerName) {
        try {
            return requestHitOrStay(playerName);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getHitOrStay(playerName);
        }
    }

    private static PlayerAnswer requestHitOrStay(String name) {
        System.out.println(System.lineSeparator() + name + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        final String text = scanner.nextLine().trim();
        return PlayerAnswer.from(text);
    }
}
