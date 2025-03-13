package blackjack.view;

import blackjack.common.ErrorMessage;
import blackjack.domain.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNames = scanner.nextLine();

        return parseNames(rawNames);
    }

    public static int readBettingMoney(String name) {
        System.out.println(name + "의 배팅 금액은?");

        return parseMoney(scanner.nextLine());
    }

    public static Confirmation askToGetMoreCard(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        return Confirmation.find(scanner.nextLine());
    }

    private static List<String> parseNames(String rawNames) {
        if (rawNames == null || rawNames.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NEED_PLAYER_MEMBERS.getMessage());
        }

        return Arrays.stream(rawNames.split(",")).toList();
    }

    private static int parseMoney(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자로 입력해야 합니다.");
        }
    }
}
