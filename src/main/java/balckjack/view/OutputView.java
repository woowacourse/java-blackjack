package balckjack.view;

import balckjack.domain.CardDeck;
import balckjack.domain.Dealer;
import balckjack.domain.Players;
import java.util.List;

public class OutputView {

    public static void printInitCardDeck(Dealer dealer, Players players) {
        final List<String> playerNames = players.getPlayerNames();
        final String names = String.join(", ", playerNames);
        System.out.println(String.format("딜러와 %s에게 2장을 나누었습니다.", names));

        final List<String> dealerCards = dealer.getCardDeck().getCardsInfo();

        System.out.println(String.format("딜러: %s", dealerCards.get(0)));

        final List<CardDeck> playerCards = players.extractCardDeck();
        for (int index = 0; index < playerNames.size(); index++) {
            System.out.println(String.format("%s카드: %s", playerNames.get(index), playerCards.get(index).getCardsInfo()));
        }
    }


}
