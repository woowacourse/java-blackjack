package blackjack.view;

import blackjack.domain.participant.Player;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String REQUEST_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉽표 기준으로 분리)";
    private static final String REQUEST_BETTING_MONEY_MESSAGE = "%s의 베팅 금액은?";
    private static final String MORE_CARD_MESSAGE = "%s는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private InputView() {
    }

    public static List<String> requestName() {
        System.out.println(REQUEST_NAME_MESSAGE);
        return Arrays.stream(scanner.nextLine().trim().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static List<Integer> requestBettingMoney(final List<String> playerNames) {
        final List<Integer> bettingMoney = new ArrayList<>();
        for (String playerName : playerNames) {
            bettingMoney.add(requestSinglePlayerBettingMoney(playerName));
        }
        return bettingMoney;
    }

    private static int requestSinglePlayerBettingMoney(final String playerName) {
        System.out.printf(NEWLINE + REQUEST_BETTING_MONEY_MESSAGE + NEWLINE, playerName);
        final String playerInputMoney = scanner.nextLine();
        try {
            return Integer.parseInt(playerInputMoney);
        } catch (NumberFormatException e) {
            OutputView.showErrorMessage("베팅 금액을 숫자로 입력하세요.");
            return requestSinglePlayerBettingMoney(playerName);
        }
    }

    public static boolean requestMoreCard(final Player player) {
        System.out.printf(NEWLINE + MORE_CARD_MESSAGE + NEWLINE, player.getName());
        final String userInput = scanner.nextLine().toLowerCase(Locale.ROOT);
        try {
            return validateMoreCardInput(userInput);
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e.getMessage());
            return requestMoreCard(player);
        }
    }

    private static boolean validateMoreCardInput(final String userInput) {
        if ("y".equals(userInput)) {
            return true;
        }
        if ("n".equals(userInput)) {
            return false;
        }
        throw new IllegalArgumentException("y 또는 n을 입력해야 합니다.");
    }
}
