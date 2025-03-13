package blackjack.view;

import blackjack.user.PlayerName;
import blackjack.user.Wallet;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<PlayerName> readNames() {
        try {
            System.out.println("게임에 참여할 사람의 이름을 영어/한글로 입력하세요. 최대 25명 참가 가능합니다.(쉼표 기준으로 분리)");
            String input = scanner.nextLine();

            validateBlank(input);
            return parseStringToList(input).stream()
                .map(PlayerName::new)
                .toList();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readNames();
        }
    }

    public Map<PlayerName, Wallet> readPlayerPrincipals(final List<PlayerName> playerNames) {
        Map<PlayerName, Wallet> playerWallet = new LinkedHashMap<>();
        for (PlayerName playerName : playerNames) {
            Wallet wallet = readPlayerPrincipal(playerName);
            playerWallet.put(playerName, wallet);
        }

        return playerWallet;
    }

    private Wallet readPlayerPrincipal(final PlayerName playerName) {
        try {
            System.out.printf("%n%s의 배팅 금액은?(1만원에서 1,000만원까지 배팅 가능합니다.)%n", playerName.getName());
            int principal = parseStringToInteger(scanner.nextLine());

            return Wallet.initialBetting(principal);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readPlayerPrincipal(playerName);
        }
    }

    public boolean readGetOneMore(final String name) {
        try {
            System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
            String input = scanner.nextLine();

            validateBlank(input);
            return YorN.fromText(input).toBoolean();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readGetOneMore(name);
        }
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
