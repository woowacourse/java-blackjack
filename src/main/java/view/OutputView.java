package view;

import domain.Card;
import domain.Dealer;
import domain.Player;
import domain.Players;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_NAME = "딜러";
    private static final String GAME_INIT_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String PLAYER_CARDS_FORMAT = "%s카드: %s";

    public static final String DELIMITER = ", ";

    public void printInitCards(Dealer dealer, Players players) {
        String namesFormat = players.getPlayers().stream().map(Player::getName)
                .collect(Collectors.joining(DELIMITER));

        System.out.println(String.format(GAME_INIT_MESSAGE, namesFormat));
        printInitDealerCards(dealer);
        printInitPlayerCards(players);
    }

    private void printInitDealerCards(Dealer dealer) {
        Card dealerFirstCard = dealer.getDealerFirstCard();

        System.out.println(String.format(PLAYER_CARDS_FORMAT, DEALER_NAME, getCardFormat(dealerFirstCard)));
    }

    private void printInitPlayerCards(Players players) {
        for (Player player : players.getPlayers()) {
            System.out.println(String.format(PLAYER_CARDS_FORMAT, player.getName(), getCardsFormat(player.getCards())));
        }
    }

    private String getCardsFormat(List<Card> cards) {
        return cards.stream()
                .map(this::getCardFormat)
                .collect(Collectors.joining(DELIMITER));
    }

    private String getCardFormat(Card card){
        return card.getNumberName() + card.getShapeName();
    }
}
