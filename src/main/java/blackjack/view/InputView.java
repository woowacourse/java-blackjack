package blackjack.view;

import blackjack.domain.player.Bet;
import blackjack.domain.player.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final String YES = "y";
    private static final String NO = "n";
    private static final String DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> responseNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = scanner.nextLine();
        validateName(names);
        return convertNameInput(names);
    }

    private static void validateName(final String nameInput) {
        if (nameInput == null || nameInput.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 참여자의 이름을 입력해주세요.");
        }
    }

    private static List<String> convertNameInput(final String nameInput) {
        return Arrays.stream(nameInput.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static String responseSelectMoreCard(final String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%n", name, YES, NO);
        return scanner.nextLine();
    }

    public static boolean oneMoreCard(final Player participant) {
        final String input = InputView.responseSelectMoreCard(participant.getName());
        if (!input.equals(YES) && !input.equals(NO)) {
            throw new IllegalArgumentException(String.format("[ERROR] %s 또는 %s으로 입력하세요.", YES, NO));
        }
        return input.equals(YES);
    }

    public static Bet responseBetAmount(final String name) {
        System.out.println();
        System.out.println(name + "의 배팅 금액은?");
        String input = scanner.nextLine();
        int betAmount = validateBetAmount(input);
        return convertBetAmountInput(betAmount);
    }

    private static int validateBetAmount(final String input) {
        validateName(input);
        int betAmount = validateTransInteger(input);
        validateRange(betAmount);

        return betAmount;
    }

    private static int validateTransInteger(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 양의 정수를 입력해주세요.");
        }
    }

    private static void validateRange(final int betAmount) {
        if (betAmount < 0) {
            throw new IllegalArgumentException("[ERROR] 양의 정수를 입력해주세요.");
        }
    }

    private static Bet convertBetAmountInput(final int betAmount) {
        return new Bet(betAmount);
    }
}
