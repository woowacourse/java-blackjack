package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BettingResult;

public class OutputView {

    private static final String DRAW_CARD_MESSAGE = "%n%s와 %s에게 2장을 나누었습니다.%n";
    private static final String CARD_INFORMATION_MESSAGE = "%s카드: %s%n";
    private static final String TOTAL_RESULT_MESSAGE = "%n%n## 최종 수익%n";
    private static final String DRAW_RESULT_MESSAGE = "%n%s카드: %s - 결과: %d";
    private static final String RESULT_STATUS_MESSAGE = "%s: %.2f%n";

    private OutputView() {
    }

    public static void printInitCard(Dealer dealer, List<Player> players) {
        System.out.printf(DRAW_CARD_MESSAGE, dealer.getName(), printNames(players));
        printDealerCard(dealer);
        printPlayersCard(players);
    }

    private static String printNames(List<Player> players) {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.joining(","));
    }

    private static void printDealerCard(Dealer dealer) {
        System.out.printf(CARD_INFORMATION_MESSAGE, dealer.getName(), cardToString(dealer.getFirstCard()));
    }

    private static void printPlayersCard(List<Player> players) {
        for (Player player : players) {
            printCard(player);
        }
    }

    public static void printCard(Gamer gamer) {
        System.out.printf(CARD_INFORMATION_MESSAGE, gamer.getName(), cardsToString(gamer.getCards()));
    }

    private static String cardsToString(final List<Card> cards) {
        return cards.stream()
            .map(OutputView::cardToString)
            .collect(Collectors.joining(", "));
    }

    private static String cardToString(Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    public static void printDealerDrawCardMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printDrawResult(Dealer dealer, List<Player> players) {
        printDrawCard(dealer);
        for (Gamer player : players) {
            printDrawCard(player);
        }
    }

    private static void printDrawCard(Gamer gamer) {
        System.out.printf(DRAW_RESULT_MESSAGE, gamer.getName(), cardsToString(gamer.getCards()), gamer.calculateScore());
    }

    public static void printTotalResult(final Dealer dealer, final BettingResult bettingResult) {
        System.out.printf(TOTAL_RESULT_MESSAGE);
        System.out.printf(RESULT_STATUS_MESSAGE, dealer.getName(), bettingResult.getDealerRevenue());
        bettingResult.getPlayerRevenue().forEach((player, revenue) ->
            System.out.printf(RESULT_STATUS_MESSAGE, player.getName(), revenue));
        System.out.println();
    }
}
