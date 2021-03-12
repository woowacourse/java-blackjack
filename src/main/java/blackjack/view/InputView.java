package blackjack.view;

import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final Scanner scanner = new Scanner(System.in);
    private static final String REQUEST_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉽표 기준으로 분리)";
    private static final String REQUEST_MONEY_MESSAGE = "%s의 베팅 금액은?";
    private static final String MORE_CARD_MESSAGE = "%s는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String INPUT_CHOICE_ERROR = "y 또는 n을 입력해야 합니다.";
    private static final String DISAGREE_MARK = "n";
    private static final String AGREE_MARK = "y";

    public static List<String> requestName() {
        System.out.println(REQUEST_NAME_MESSAGE);
        return Arrays.stream(scanner.nextLine().trim().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static boolean askPlayerMoreCard(final Player player) {
        System.out.printf(NEWLINE + MORE_CARD_MESSAGE + NEWLINE, player.getName());
        try {
            final String userInput = scanner.nextLine().toLowerCase(Locale.ROOT);
            return validateMoreCardOption(userInput);
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e.getMessage());
            return askPlayerMoreCard(player);
        }
    }

    private static boolean validateMoreCardOption(final String userInput) {
        if (AGREE_MARK.equals(userInput) || DISAGREE_MARK.equals(userInput)) {
            return AGREE_MARK.equals(userInput);
        }
        throw new IllegalArgumentException(INPUT_CHOICE_ERROR);
    }

    public static List<Integer> requestBettingMoney(final List<String> names) {
        return names.stream()
                .map(InputView::requestEachBettingMoney)
                .collect(Collectors.toList());
    }

    private static int requestEachBettingMoney(final String name) {
        try {
            System.out.printf(NEWLINE + REQUEST_MONEY_MESSAGE + NEWLINE, name);
            return Integer.parseInt(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e.getMessage());
            return requestEachBettingMoney(name);
        }
    }
}
