package blackjack.view;

import blackjack.user.player.Player;
import blackjack.user.player.PlayerName;
import blackjack.user.player.BetAmount;
import blackjack.user.player.Players;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String ERROR_MESSAGE_SUFFIX = " 다시 입력해주세요.";

    private final Scanner scanner = new Scanner(System.in);

    public Players readPlayers() {
        try {
            String input = scanner.nextLine();
            validateBlank(input);

            List<Player> players = parseStringToList(input).stream()
                .map(name -> Player.createPlayer(new PlayerName(name), BetAmount.initAmount()))
                .toList();
            return new Players(players);
        } catch (IllegalArgumentException e) {
            printErrorMessage(e.getMessage() + ERROR_MESSAGE_SUFFIX);
            return readPlayers();
        }
    }

    public BetAmount readPlayerPrincipal() {
        try {
            int principal = parseStringToInteger(scanner.nextLine());
            return BetAmount.initAmountWithPrincipal(principal);
        } catch (IllegalArgumentException e) {
            printErrorMessage(e.getMessage() + ERROR_MESSAGE_SUFFIX);
            return readPlayerPrincipal();
        }
    }

    public boolean readGetOneMore() {
        try {
            String input = scanner.nextLine();
            validateBlank(input);

            return YorN.fromText(input).toBoolean();
        } catch (IllegalArgumentException e) {
            printErrorMessage(e.getMessage() + ERROR_MESSAGE_SUFFIX);
            return readGetOneMore();
        }
    }

    public void printErrorMessage(String message) {
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
