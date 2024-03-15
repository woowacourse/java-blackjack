package view;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;
import dto.TotalResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
        System.out.println();
    }

    private static void printHandOutMessage(final Dealer dealer, final Players players) {
        String dealerName = dealer.getName().getValue();
        StringJoiner playerNames = new StringJoiner(",");
        for (Player player : players.getPlayers()) {
            playerNames.add(player.getName().getValue());
        }
        String message = String.format(System.lineSeparator() + "%s와 %s에게 %d장을 나누었습니다.", dealerName, playerNames,
                INITIAL_CARD_COUNT);
        System.out.println(message);
    }

    private static void printDealerCard(final Dealer dealer) {
        System.out.println(dealer.getName().getValue() + ": " + printCardInfo(dealer.getCard().get(0)));
    }

    private static void printPlayersCards(final Players players) {
        for (Player player : players.getPlayers()) {
            printAllCards(player);
        }
    }

    public static void printAllCards(final Player player) {
        String cardInfos = String.join(", ", printCardInfos(player.getCard()));
        String message = String.format("%s 카드: %s", player.getName().getValue(), cardInfos);
        System.out.println(message);
    }

    public static void printDealerHit(final Dealer dealer) {
        String dealerName = dealer.getName().getValue();
        String message = String.format(System.lineSeparator() + "%s는 %d이하라 한장의 카드를 더 받았습니다.", dealerName,
                DEALER_HIT_CONDITION);
        System.out.println(message);
    }

    public static void printCardsAndResult(final Dealer dealer, final Players players) {
        StringBuilder builder = new StringBuilder(System.lineSeparator());
        for (Gamer gamer : getGamers(dealer, players)) {
            String gamerName = gamer.getName().getValue();
            int totalScore = gamer.calculateTotalScore();
            String cardInfos = String.join(", ", printCardInfos(gamer.getCard()));
            builder.append(String.format("%s 카드: %s - 결과: %d", gamerName, cardInfos, totalScore))
                    .append(System.lineSeparator());
        }
        System.out.println(builder);
    }

    private static List<Gamer> getGamers(final Dealer dealer, final Players players) {
        List<Gamer> gamers = new ArrayList<>();
        gamers.add(dealer);
        gamers.addAll(players.getPlayers());
        return gamers;
    }

    private static List<String> printCardInfos(final List<Card> cards) {
        List<String> cardInfos = new ArrayList<>();
        for (Card card : cards) {
            cardInfos.add(printCardInfo(card));
        }
        return cardInfos;
    }

    private static String printCardInfo(final Card card) {
        String symbol = SymbolView.findName(card.getSymbol());
        String rank = RankView.findName(card.getRank());
        return rank + symbol;
    }

    public static void printTotalResult(final TotalResult totalResult) {
        System.out.println(System.lineSeparator() + "## 최종 수익");
        String message = String.format("딜러: %s", totalResult.getDealerProfit());
        System.out.println(message);
        printPlayerResults(totalResult.getPlayerResult());
    }

    private static void printPlayerResults(final Map<Player, BigDecimal> playerResults) {
        StringBuilder builder = new StringBuilder();
        for (Entry<Player, BigDecimal> playerResult : playerResults.entrySet()) {
            String playerName = playerResult.getKey().getName().getValue();
            String profit = playerResult.getValue().toString();
            String message = String.format("%s: %s", playerName, profit);
            builder.append(message).append(System.lineSeparator());
        }
        System.out.println(builder);
    }
}
