package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.List;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String SEPARATOR = ": ";


    public static void printHandOutCardsMessage(Dealer dealer, List<Player> players) {
        System.out.print(dealer.getName() + "와 ");
        List<String> playerNames = new ArrayList<>();

        for (Player player : players) {
            playerNames.add(player.getName());
        }
        System.out.println(String.join(", ", playerNames) + "에게 두 장의 카드를 나눠줬습니다.");
    }

    public static void printParticipantCards(String participantName, Cards cards) {
        System.out.print(participantName + "카드: ");
        List<String> results = new ArrayList<>();

        for (Card card : cards.getCards()) {
            results.add(card.getType().getName() + card.getDenomination().getName());
        }
        System.out.println(String.join(", ", results));
    }

    public static void printDealerCardDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printParticipantCardsWithScore(String participantName, Cards cards) {
        System.out.print(participantName + "카드: ");
        List<String> results = new ArrayList<>();

        for (Card card : cards.getCards()) {
            results.add(card.getType().getName() + card.getDenomination().getName());
        }
        System.out.println(String.join(", ", results) + " - 결과: " + cards.getScore());
    }


    public static void printGameResult(List<Player> players, Dealer dealer) {
        System.out.println("\n## 최종승패");

        System.out.print(dealer.getName() + " ");
        if (dealer.getWinCount() > 0) {
            System.out.print(dealer.getWinCount() + "승 ");
        }
        if (dealer.getDrawCount() > 0) {
            System.out.print(dealer.getDrawCount() + "무 ");
        }
        if (dealer.getLoseCount() > 0) {
            System.out.print(dealer.getLoseCount() + "패 ");
        }
        System.out.println();

        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getResult().getName());
        }
    }
}
