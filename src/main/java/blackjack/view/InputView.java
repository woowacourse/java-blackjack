package blackjack.view;

import blackjack.user.Player;
import blackjack.user.PlayerName;
import blackjack.game.betting.BetAmount;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<PlayerName> readNames() {
        try {
            String input = scanner.nextLine();
            validateBlank(input);

            return parseStringToList(input).stream()
                .map(PlayerName::new)
                .toList();
        } catch (IllegalArgumentException e) {
            printMessage(e.getMessage());
            return readNames();
        }
    }

    public Map<PlayerName, BetAmount> readPlayerPrincipals(List<PlayerName> names) {
        Map<PlayerName, BetAmount> playerAmounts = new LinkedHashMap<>();
        for (PlayerName name : names) {
            System.out.println();
            printMessage(String.format("%s의 배팅 금액은?(1만원에서 1,000만원까지 배팅 가능합니다.)", name.getText()));
            BetAmount betAmount = readPlayerPrincipal();

            playerAmounts.put(name, betAmount);
        }
        return playerAmounts;
    }

    private BetAmount readPlayerPrincipal() {
        try {
            int principal = parseStringToInteger(scanner.nextLine());
            return BetAmount.initialBetting(principal);
        } catch (IllegalArgumentException e) {
            printMessage(e.getMessage());
            return readPlayerPrincipal();
        }
    }

    public boolean readGetOneMore(final Player player) {
        try {
            printMessage(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName().getText()));
            String input = scanner.nextLine();
            validateBlank(input);

            return YorN.fromText(input).toBoolean();
        } catch (IllegalArgumentException e) {
            printMessage(e.getMessage());
            return readGetOneMore(player);
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    private List<String> parseStringToList(final String input) {
        return Arrays.asList(input.split(","));
    }

    private int parseStringToInteger(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    private void validateBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력값이 없습니다.");
        }
    }

    enum YorN {
        YES("y", true),
        NO("n", false);

        private final String text;
        private final boolean isYes;

        YorN(String text, boolean isYes) {
            this.text = text;
            this.isYes = isYes;
        }

        private static YorN fromText(final String input) {
            return Arrays.stream(values())
                .filter(value -> value.text.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력은 y/n만 가능합니다."));
        }

        private boolean toBoolean() {
            return isYes;
        }
    }
}
