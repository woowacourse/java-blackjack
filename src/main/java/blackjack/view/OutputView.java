package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Result;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.List;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String NAME_SEPARATOR = ": ";


    public static void printHandOutCardsMessage(Dealer dealer, List<Player> players) {
        System.out.print(dealer.getName() + "와 ");
        List<String> playerNames = new ArrayList<>();

        for (Player player : players) {
            playerNames.add(player.getName());
        }
        System.out.println(String.join(DELIMITER, playerNames) + "에게 두 장의 카드를 나눠줬습니다.");
    }

    public static void printParticipantCards(String participantName, Cards cards) {
        System.out.print(participantName + "카드: ");
        List<String> results = new ArrayList<>();

        for (Card card : cards.getCards()) {
            results.add(card.getType().getName() + card.getDenomination().getName());
        }
        System.out.println(String.join(DELIMITER, results));
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
        System.out.println(String.join(DELIMITER, results) + " - 결과: " + cards.getScore());
    }


    public static void printGameResult(List<Player> players, Dealer dealer) {
        System.out.println("\n## 최종승패");
        printDealerGameResult(dealer);
        System.out.print(NEW_LINE);
        printPlayersGameResults(players);
    }

    private static void printDealerGameResult(Dealer dealer){
        System.out.print(dealer.getName() + " ");
        if (dealer.getResultCount(Result.WIN) > 0) {
            System.out.print(dealer.getResultCount(Result.WIN) + "승 ");
        }
        if (dealer.getResultCount(Result.DRAW) > 0) {
            System.out.print(dealer.getResultCount(Result.DRAW) + "무 ");
        }
        if (dealer.getResultCount(Result.LOSE) > 0) {
            System.out.print(dealer.getResultCount(Result.LOSE) + "패 ");
        }
    }

    private static void printPlayersGameResults(List<Player> players){
        for (Player player : players) {
            System.out.println(player.getName() + NAME_SEPARATOR + player.getResult().getName());
        }
    }

    public static void printDealerCards(String participantName, Cards cards) {
        System.out.print(participantName + "카드: ");

        Card card = cards.getCards().get(0);
        List<String> results = new ArrayList<>();

        results.add(card.getType().getName() + card.getDenomination().getName());
        System.out.println(String.join(DELIMITER, results));
    }
}
