package blackjack.view;

import static blackjack.common.Constants.LINE_SEPARATOR;

import blackjack.common.ErrorMessage;
import blackjack.domain.Player;
import java.util.ArrayList;
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

    private static List<String> parseNames(String rawNames) {
        if (rawNames == null || rawNames.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NEED_PLAYER_MEMBERS.getMessage());
        }

        List<String> names = Arrays.stream(rawNames.split(","))
                .toList();

        validNames(names);
        return names;
    }

    private static void validNames(List<String> names) {
        names.stream()
                .filter(name -> !name.isBlank())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.USE_VALID_NAME.getMessage()));
    }

    public static List<Integer> readBettingMoneyList(List<String> names) {
        List<Integer> moneyList = new ArrayList<>();

        for (String name : names) {
            System.out.println(LINE_SEPARATOR + name + "의 배팅 금액은?");
            moneyList.add(parseMoney(scanner.nextLine()));
        }

        return moneyList;
    }

    private static int parseMoney(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자로 입력해야 합니다.");
        }
    }

    public static Confirmation askToGetMoreCard(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        return Confirmation.find(scanner.nextLine());
    }
}
