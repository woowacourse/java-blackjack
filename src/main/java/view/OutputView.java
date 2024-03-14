package view;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {
    private static final int INITIAL_CARD_COUNT = 2;
    private static final int DEALER_HIT_CONDITION = 16;

    private OutputView() {
    }

    public static void printInitialCardsMessage(final Dealer dealer, final Players players) {
        printHandOutMessage(dealer, players);
        printDealerCard(dealer);
        printPlayersCards(players);
        printNewLine();
    }

    private static void printHandOutMessage(final Dealer dealer, final Players players) {
        String dealerName = dealer.getName();
        StringJoiner playerNames = new StringJoiner(",");
        for (Player player : players.getPlayers()) {
            playerNames.add(player.getName());
        }
        System.out.printf("%n%s와 %s에게 %d장을 나누었습니다.%n", dealerName, playerNames, INITIAL_CARD_COUNT);
    }

    private static void printDealerCard(final Dealer dealer) {
        System.out.println(dealer.getName() + ": " + makeCardInfo(dealer.openFirstCard()));
    }

    private static void printPlayersCards(final Players players) {
        for (Player player : players.getPlayers()) {
            printAllCards(player);
        }
    }

    public static void printAllCards(final Player player) {
        String cardInfos = String.join(", ", makeCardInfos(player.getCardsInHand()));
        System.out.printf("%s카드: %s%n", player.getName(), cardInfos);
    }

    public static void printDealerHit(final Dealer dealer) {
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.%n",  dealer.getName(), DEALER_HIT_CONDITION);
    }

    public static void printCardsAndResult(final Dealer dealer, final Players players) {
        StringBuilder builder = new StringBuilder();
        builder.append(makeCardsAndResultMessage(dealer))
                .append(System.lineSeparator());

        for (Player player : players.getPlayers()) {
            builder.append(makeCardsAndResultMessage(player))
                    .append(System.lineSeparator());
        }
        System.out.println(builder);
    }

    private static String makeCardsAndResultMessage(final Gamer gamer) {
        String gamerName = gamer.getName();
        int totalScore = gamer.calculateTotalScore();
        String cardInfos = String.join(", ", makeCardInfos(gamer.getCardsInHand()));
        return String.format("%s 카드: %s - 결과: %d", gamerName, cardInfos, totalScore);
    }

    private static List<String> makeCardInfos(final List<Card> cards) {
        List<String> cardInfos = new ArrayList<>();
        for (Card card : cards) {
            cardInfos.add(makeCardInfo(card));
        }
        return cardInfos;
    }

    private static String makeCardInfo(final Card card) {
        String symbol = card.getSymbolValue();
        String rank = card.getRankValue();
        return rank + symbol;
    }

    public static void printFinalGameResult(final int dealerProfit, final Map<Player, Integer> playersResult) {
        System.out.println("## 최종 승패");
        StringBuilder builder = new StringBuilder(String.format("딜러: %d%n", dealerProfit));
        playersResult.entrySet().stream()
                .map(result -> String.format("%s: %s%n", result.getKey().getName(), result.getValue()))
                .forEach(builder::append);
        System.out.println(builder);
    }

    public static void printNewLine() {
        System.out.println();
    }
}
