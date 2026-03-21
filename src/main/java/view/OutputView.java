package view;

import domain.card.Card;
import domain.game.BlackjackStatistics;
import domain.game.PlayerProfit;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String PRINT_PLAYERS_MESSAGE = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String PRINT_HAND_MESSAGE = "%s카드: %s";
    private static final String PRINT_DEALER_HIT = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PRINT_BLACKJACK_RESULT_MESSAGE = " - 결과: %d\n";
    private static final String PRINT_BLACKJACK_STATISTICS_HEADER_MESSAGE = "## 최종 수익";
    private static final String PRINT_BLACKJACK_STATISTICS_DEALER_MESSAGE = "딜러: %d\n";
    private static final String PRINT_BLACKJACK_STATISTICS_PLAYER_MESSAGE = "%s: %d\n";

    public void printPlayers(List<Player> players) {
        List<String> names = players.stream()
                .map(Player::getName)
                .toList();
        System.out.printf(PRINT_PLAYERS_MESSAGE, String.join(DELIMITER, names));
    }

    public void printHandList(Dealer dealer, List<Player> players) {
        printlnHand(dealer.getName(), dealer.getOnlyFirstHand());
        for (Player player : players) {
            printlnHand(player.getName(), player.getHand());
        }
        System.out.println();
    }

    private void printHand(String name, List<Card> hand) {
        System.out.printf(PRINT_HAND_MESSAGE, name, convertCardsToString(hand));
    }

    public void printlnHand(String name, List<Card> hand) {
        printHand(name, hand);
        System.out.println();
    }

    public void printDealerHit() {
        System.out.println(PRINT_DEALER_HIT);
    }

    public void printBlackjackResult(Dealer dealer, List<Player> players) {
        System.out.println();
        printHand(dealer.getName(), dealer.getHand());
        System.out.printf(PRINT_BLACKJACK_RESULT_MESSAGE, dealer.calculateScore());
        for (Player player : players) {
            printHand(player.getName(), player.getHand());
            System.out.printf(PRINT_BLACKJACK_RESULT_MESSAGE, player.calculateScore());
        }
        System.out.println();
    }

    public void printBlackjackStatistics(BlackjackStatistics blackjackStatistics) {
        System.out.println(PRINT_BLACKJACK_STATISTICS_HEADER_MESSAGE);
        System.out.printf(PRINT_BLACKJACK_STATISTICS_DEALER_MESSAGE, blackjackStatistics.dealerProfit());
        for (PlayerProfit playerProfit : blackjackStatistics.playerProfits()) {
            System.out.printf(PRINT_BLACKJACK_STATISTICS_PLAYER_MESSAGE,
                    playerProfit.name(), playerProfit.profit());
        }
    }

    private String convertCardsToString(List<Card> cards) {
        List<String> cardStrings = cards.stream()
                .map(card -> card.rank().getRank() + card.suit().getSuit())
                .toList();
        return String.join(DELIMITER, cardStrings);
    }
}
