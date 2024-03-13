package blackjack.view;

import blackjack.domain.player.PlayerName;
import blackjack.domain.bet.BetAmount;
import blackjack.exception.NeedRetryException;
import blackjack.view.format.CardRequestFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";

    public List<PlayerName> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = SCANNER.nextLine();

        validateDelimiter(input);

        return Arrays.stream(input.split(DELIMITER))
                .map(PlayerName::new)
                .toList();
    }

    private void validateDelimiter(final String input) {
        if (input.endsWith(DELIMITER)) {
            throw new NeedRetryException(DELIMITER + " 로 끝날 수 없습니다.");
        }
    }

    public Map<PlayerName, BetAmount> readBetAmounts(final List<PlayerName> names) {
        final Map<PlayerName, BetAmount> playerBetAmount = new HashMap<>();

        for (final PlayerName name : names) {
            System.out.printf("%n%s의 배팅 금액은?%n", name.getName());
            final BetAmount betAmount = readBetAmount();
            playerBetAmount.put(name, betAmount);
        }

        return playerBetAmount;
    }

    private BetAmount readBetAmount() {
        try {
            return new BetAmount(readInt());
        } catch (final NeedRetryException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return readBetAmount();
        }
    }

    private int readInt() {
        final String input = SCANNER.nextLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NeedRetryException("숫자만 입력할 수 있습니다.");
        }
    }

    public boolean readNeedMoreCard(final PlayerName name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%n", name.getName(),
                CardRequestFormat.YES.getFormat(),
                CardRequestFormat.NO.getFormat());

        final String input = SCANNER.nextLine();

        return CardRequestFormat.from(input);
    }
}
