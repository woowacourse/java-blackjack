package view;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import domain.result.PlayerResults;
import domain.result.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {
    private static final int INITIAL_CARD_COUNT = 2;
    private static final int DEALER_HIT_CONDITION = 16;

    private OutputView() {

    }

    public static void printInitialCardsMessage(final Gamers gamers) {
        Dealer dealer = gamers.findDealer();
        List<Player> players = gamers.findPlayers();

        printHandOutMessage(dealer, players);
        printDealerCard(dealer);
        printPlayersCards(players);
        System.out.println();
    }

    private static void printHandOutMessage(final Dealer dealer, final List<Player> players) {
        String dealerName = dealer.getName();
        StringJoiner playerNames = new StringJoiner(",");
        for (Player player : players) {
            playerNames.add(player.getName());
        }
        String message = String.format(System.lineSeparator() + "%s와 %s에게 %d장을 나누었습니다.", dealerName, playerNames,
                INITIAL_CARD_COUNT);
        System.out.println(message);
    }

    private static void printDealerCard(final Dealer dealer) {
        System.out.println(dealer.getName() + ": " + printCardInfo(dealer.openFirstCard()));
    }

    private static void printPlayersCards(final List<Player> players) {
        for (Player player : players) {
            printAllCards(player);
        }
    }

    public static void printAllCards(final Player player) {
        String cardInfos = String.join(", ", printCardInfos(player.getCardsInHand()));
        String message = String.format("%s카드: %s", player.getName(), cardInfos);
        System.out.println(message);
    }

    public static void printDealerHit(final Dealer dealer) {
        String dealerName = dealer.getName();
        String message = String.format(System.lineSeparator() + "%s는 %d이하라 한장의 카드를 더 받았습니다.", dealerName,
                DEALER_HIT_CONDITION);
        System.out.println(message);
    }

    public static void printCardsAndResult(final Gamers gamers) {
        StringBuilder builder = new StringBuilder();
        for (Gamer gamer : gamers.getGamers()) {
            String gamerName = gamer.getName();
            int totalScore = gamer.calculateTotalScore();
            String cardInfos = String.join(", ", printCardInfos(gamer.getCardsInHand()));
            builder.append(String.format("%s 카드: %s - 결과: %d", gamerName, cardInfos, totalScore))
                    .append(System.lineSeparator());
        }
        System.out.println(builder);
    }

    private static List<String> printCardInfos(final List<Card> cards) {
        List<String> cardInfos = new ArrayList<>();
        for (Card card : cards) {
            cardInfos.add(printCardInfo(card));
        }
        return cardInfos;
    }

    private static String printCardInfo(final Card card) {
        String symbol = card.getSymbolValue();
        String rank = card.getRankValue();
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
        Set<Entry<Player, Result>> resultEntrySet = playerResults.getResults().entrySet();
        String resultString = resultEntrySet.stream()
                .map(entry -> String.format("%s: %s", entry.getKey().getName(), entry.getValue().getName()))
                .collect(Collectors.joining(System.lineSeparator()));

        System.out.println(resultString);
    }
}
