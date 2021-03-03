package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import java.util.ArrayList;
import java.util.List;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    public static final String STRING_DELIMITER = ", ";
    public static final String INITIAL_CARD_MESSAGE = "딜러와 %s에게 카드를 2장씩 나누었습니다.";
    public static final String DEALER_CARD = "딜러: %s";
    public static final String PLAYER_CARD = "%s카드: %s";

    private OutputView() {};

    public static void showInitiate(Dealer dealer, Players players) {
        showName(players);
        showCards(dealer, players);
    }

    private static void showCards(Dealer dealer, Players players) {
        String dealerCard = dealer.getUserDeck().getUserCards().get(1).getCard();
        System.out.printf(DEALER_CARD + NEWLINE, dealerCard);
        for (Player player : players.getPlayers()) {
            String playerName = player.getName();
            String cards = combineAllCard(player);
            System.out.printf(PLAYER_CARD + NEWLINE, playerName, cards);
        }
    }

    private static void showName(Players players) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : players.getPlayers()) {
            stringBuilder.append(player.getName());
        }
        String nameCollect = stringBuilder.toString();
        System.out.printf(INITIAL_CARD_MESSAGE + NEWLINE, nameCollect);
    }

    private static String combineAllCard(User user) {
        List<String> allCards = new ArrayList<>();
        for (Card card : user.getUserDeck().getUserCards()) {
            allCards.add(card.getCard());
        }
        return String.join(STRING_DELIMITER, allCards);
    }

}
