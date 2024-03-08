package view;

import domain.Card;
import domain.Dealer;
import domain.Gamer;
import domain.Player;
import domain.PlayerResults;
import domain.Players;
import domain.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringJoiner;

public class OutputView {


    private static final int INITIAL_CARD_COUNT = 2;
    private static final int DEALER_HIT_CONDITION = 16;

    private OutputView() {

    }

    public static void printHandOutCardMessage(Dealer dealer, Players players) {
        String dealerName = dealer.getName().getValue();
        StringJoiner playerNames = new StringJoiner(",");
        for (Player player : players.getPlayers()) {
            playerNames.add(player.getName().getValue());
        }
        String message = String.format(System.lineSeparator() + "%s와 %s에게 %d장을 나누었습니다.", dealerName, playerNames,
                INITIAL_CARD_COUNT);
        System.out.println(message);
        printWithoutHiddenCard(dealer);
        for (Player player : players.getPlayers()) {
            printAllCards(player);
        }
        System.out.println();
    }

    private static void printWithoutHiddenCard(final Dealer dealer) {
        System.out.println(dealer.getName().getValue() + ": " + printCard(dealer.getHand().get(0)));
    }

    public static void printAllCards(final Player player) {
        String cardInfos = String.join(", ", printCards(player.getHand()));
        String message = String.format("%s카드: %s", player.getName().getValue(), cardInfos);
        System.out.println(message);
    }

    public static void printDealerHit(Dealer dealer) {
        String dealerName = dealer.getName().getValue();
        String message = String.format(System.lineSeparator() + "%s는 %d이하라 한장의 카드를 더 받았습니다.", dealerName,
                DEALER_HIT_CONDITION);
        System.out.println(message);
    }

    public static void printCardsAndResult(Gamer gamer) {
        String gamerName = gamer.getName().getValue();
        int totalScore = gamer.calculateTotalScore();
        String cardInfos = String.join(", ", printCards(gamer.getHand()));
        String message = String.format("%s 카드: %s - 결과: %d", gamerName, cardInfos, totalScore);
        System.out.println(message);
    }

    private static List<String> printCards(List<Card> cards) {
        List<String> cardInfos = new ArrayList<>();
        for (Card card : cards) {
            cardInfos.add(printCard(card));
        }
        return cardInfos;
    }

    private static String printCard(final Card card) {
        String symbol = card.getSymbol().getName();
        String rank = card.getRank().getName();
        return rank + symbol;
    }

    public static void printFinalGameResult(final PlayerResults playerResults) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        int winCount = playerResults.findWinCount();
        int loseCount = playerResults.findLoseCount();
        int tieCount = playerResults.findTieCount();
        String message = String.format("딜러: %d승 %d패 %d무", loseCount, winCount, tieCount);
        System.out.println(message);
        printPlayerResults(playerResults);
    }

    private static void printPlayerResults(final PlayerResults playerResults) {
        StringBuilder builder = new StringBuilder();
        for (Entry<Player, Result> player : playerResults.getResults().entrySet()) {
            String playerName = player.getKey().getName().getValue();
            String result = player.getValue().getName();
            String message = String.format("%s: %s", playerName, result);
            builder.append(message)
                    .append(System.lineSeparator());
        }
        System.out.println(builder);
    }
}
