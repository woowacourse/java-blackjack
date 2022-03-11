package blackjack.view;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

import blackjack.domain.card.Card;
import blackjack.domain.Result;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    public static void showParticipantsHand(Dealer dealer, List<Player> players) {
        out.printf(NEW_LINE + "딜러와 %s에게 2장의 카드를 나누었습니다." + NEW_LINE, getPlayerNames(players));
        out.printf("%s: %s" + NEW_LINE, dealer.getName(),
                cardDisplayContents(dealer.getOpenCard()));
        for (Player player : players) {
            printPlayerHand(player);
        }
    }

    private static String getPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(joining(", "));
    }

    public static void showPlayerHand(Player player) {
        printPlayerHand(player);
    }

    private static void printPlayerHand(Player player) {
        out.printf("%s카드: %s" + NEW_LINE, player.getName(), getCards(player));
    }

    private static String getCards(Player player) {
        return player.getCards().stream()
                .map(card -> cardDisplayContents(card))
                .collect(joining(", "));
    }

    public static void printDealerHandDrawMessage() {
        out.println("딜러는 16이하라 한장의 카드를 더 받았습니다." + NEW_LINE);
    }

    public static void printParticipantScore(Dealer dealer, List<Player> players) {
        String dealerCardDisplayContents = dealer.getCards().stream()
                .map(card -> cardDisplayContents(card))
                .collect(joining(", "));

        out.printf("%s 카드: %s - 결과: %d" + NEW_LINE, dealer.getName(), dealerCardDisplayContents, dealer.getScore());

        for (Player player : players) {
            out.printf("%s카드: %s - 결과: %d" + NEW_LINE, player.getName(), getCards(player),
                    player.getCardHand().getScore());
        }
    }

    private static String cardDisplayContents(Card card) {
        return card.getDenomination() + card.getSuit();
    }

    public static void printBlackjackGameResult(Dealer dealer, List<Player> players) {
        out.println(NEW_LINE + "## 최종 승패");

        printDealerResult(dealer);

        printPlayerResult(players);
    }

    private static void printDealerResult(Dealer dealer) {
        Map<Result, Integer> resultScores = dealer.getResultScores();
        String dealerScoreString = resultScores.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .map(entry -> entry.getValue() + entry.getKey().getName() + " ")
                .collect(joining(" "));
        out.println("딜러: " + dealerScoreString);
    }

    private static void printPlayerResult(List<Player> players) {
        for (Player player : players) {
            out.printf("%s: %s" + NEW_LINE, player.getName(), player.getResult().getName());
        }
    }
}
