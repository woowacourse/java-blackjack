package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import java.util.List;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR]: ";
    private static final String START_NUMBER_OF_CARD = "2";

    public static void printErrorMessage(final String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printStartMessage(final List<Player> players, final Dealer dealer) {
        List<String> playerNicknames = players.stream()
            .map(Player::getNickname)
            .toList();

        System.out.printf("딜러와 %s에게 %s장을 나누었습니다.\n",
            String.join(", ", playerNicknames), START_NUMBER_OF_CARD);

        System.out.printf("딜러카드: %s\n", String.join(", ", dealer.getOpenedCardNames()));

        players.forEach(player -> {
            System.out.printf(player.getNickname() + "카드: ");
            System.out.println(String.join(", ", player.getCardNames()));
        });
    }


}
