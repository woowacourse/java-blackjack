package balckjack.view;

import balckjack.domain.CardDeck;
import balckjack.domain.Dealer;
import balckjack.domain.Participant;
import balckjack.domain.Player;
import balckjack.domain.Players;
import java.util.List;

public class OutputView {

    public static void printInitCardDeck(Participant dealer, Players players) {
        System.out.println();
        final List<String> playerNames = players.getPlayerNames();
        final String names = String.join(", ", playerNames);
        System.out.println(String.format("딜러와 %s에게 2장을 나누었습니다.", names));

        final List<String> dealerCards = dealer.getCardDeck().getCardsInfo();

        System.out.println(String.format("딜러: %s", dealerCards.get(0)));

        for (int index = 0; index < playerNames.size(); index++) {
            printParticipantCardDeck(players.getPlayers().get(index));
        }
        System.out.println();
    }

    public static void printParticipantCardDeck(Player player) {
        final String cards = String.join(", ", player.getCardDeck().getCardsInfo());
        System.out.println(player.getName().getValue() + "카드: " + cards);
    }

}
