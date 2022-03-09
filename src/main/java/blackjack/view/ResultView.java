package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.domain.Name;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Card;
import java.util.stream.Collectors;

public class ResultView {

    private static final String PRINT_INIT_CARD_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String NAME_DELIMITER = ": ";
    private static final String CARD_DELIMITER = ", ";

    public static void printInitCard(Dealer dealer, Players players) {
        System.out.println(String.format(PRINT_INIT_CARD_FORMAT, printPlayersName(players)));
        System.out.println(dealer.getName().get() + NAME_DELIMITER + getNumberAndType(dealer.getCards().get(0)));
        printPlayersCard(players);
        System.out.println();
    }

    private static String printPlayersName(Players players) {
        return players.get().stream()
                .map(Player::getName)
                .map(Name::get)
                .collect(Collectors.joining(CARD_DELIMITER));
    }

    private static void printPlayersCard(Players players) {
        for (Player player : players.get()) {
            System.out.print(player.getName().get() + NAME_DELIMITER);
            String cards = player.getCards().stream()
                    .map(ResultView::getNumberAndType)
                    .collect(Collectors.joining(CARD_DELIMITER));
            System.out.println(cards);
        }
    }

    private static String getNumberAndType(Card card) {
        return card.getCardNumber().getNumber() + card.getType().getName();
    }
}
