package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String INITIAL_PRINT_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다." + NEWLINE;
    private static final String CARD_INFO_FORMAT = "%s 카드: %s";
    private static final String DEALER_ADD_CARD_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다." + NEWLINE;
    private static final String RESULT_WITH_POINTS = "%s - 결과: %d" + NEWLINE;
    private static final String RESULT = "%s: %.0f" + NEWLINE;
    private static final String RESULT_TITLE = "## 최종 수익";

    public static void printInitialCards(Dealer dealer, Players players) {
        printNewLine();
        System.out.printf(INITIAL_PRINT_FORMAT,
                String.join(", ", players.getNames()));
        printNewLine();
        printInitialHand(dealer, players);
    }

    private static void printInitialHand(Dealer dealer, Players players) {
        System.out.printf(CARD_INFO_FORMAT, dealer.getName()
                , cardToString(dealer.showOpenHands()));
        printNewLine();
        for (Player player : players) {
            System.out.printf(CARD_INFO_FORMAT, player.getName(),
                    cardToString(player.showOpenHands()));
            printNewLine();
        }
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printAllCards(Gamer gamer) {
        System.out.printf(CARD_INFO_FORMAT + NEWLINE, gamer.getName(), cardToString(gamer.showHands()));
    }

    public static void showAllCards(Dealer dealer, Players players) {
        allCardsWithPoint(dealer);
        players.forEach(OutputView::allCardsWithPoint);
    }

    public static void allCardsWithPoint(Gamer gamer) {
        System.out.printf(RESULT_WITH_POINTS,
                String.format(CARD_INFO_FORMAT, gamer.getName(), cardToString(gamer.showHands())), gamer.getScore());
    }

    private static String cardToString(List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public static void printDealerHitMessage() {
        System.out.println(DEALER_ADD_CARD_MSG);
    }

    public static void printResultTitle() {
        printNewLine();
        System.out.println(RESULT_TITLE);
    }

    public static void printProfit(Map<String, Double> result) {
        for (Map.Entry<String, Double> entry : result.entrySet()) {
            System.out.printf(RESULT, entry.getKey(), entry.getValue());
        }
    }
}
