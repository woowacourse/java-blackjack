package blackjack.view;

import blackjack.model.betting.BettingMoney;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_BETTING_MONEY_TO_PLAYER = "\n%s의 배팅 금액은?";
    private static final String ASK_DRAW_OR_STAND_COMMAND_TO_PLAYER = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String INVALID_BETTING_MONEY_AMOUNT = "배팅 금액은 숫자로 입력해야 합니다.";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> askPlayerNames() {
        System.out.println(ASK_PLAYER_NAMES);
        String input = scanner.nextLine();
        return convertInputToPlayerNames(input);
    }

    private List<String> convertInputToPlayerNames(final String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
    }

    public BettingMoney askBettingMoneyToPlayer(final String playerName) {
        System.out.println(formatAskBettingMoneyToPlayer(playerName));
        String input = scanner.nextLine();
        return convertInputToBettingMoney(input);
    }

    private String formatAskBettingMoneyToPlayer(final String playerName) {
        return String.format(ASK_BETTING_MONEY_TO_PLAYER, playerName);
    }

    private BettingMoney convertInputToBettingMoney(final String input) {
        int bettingMoneyAmount = parseInputToBettingMoneyAmount(input);
        return parseBettingMoneyAmountToBettingMoney(bettingMoneyAmount);
    }

    private int parseInputToBettingMoneyAmount(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BETTING_MONEY_AMOUNT);
        }
    }

    private BettingMoney parseBettingMoneyAmountToBettingMoney(final int amount) {
        return new BettingMoney(amount);
    }

    public Command askDrawOrStandCommandToPlayer(final String name) {
        System.out.println(formatAskDrawOrStandCommandToPlayer(name));
        String input = scanner.nextLine();
        return Command.from(input);
    }

    private String formatAskDrawOrStandCommandToPlayer(final String name) {
        return String.format(ASK_DRAW_OR_STAND_COMMAND_TO_PLAYER, name);
    }
}
