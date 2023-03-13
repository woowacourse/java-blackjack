package view;

import domain.*;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ",";

    public static void printStart(Dealer dealer, Players players) {
        printGiveMessage(dealer, players);
        printHideCard(dealer);
        players.getPlayers().forEach(OutputView::printCard);
        System.out.println();
    }

    private static void printGiveMessage(Dealer dealer, Players players) {
        String playersNames = players.getPlayers().stream()
            .map(Participant::getName)
            .collect(Collectors.joining(","));
        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), playersNames);
    }

    private static void printHideCard(Dealer dealer) {
        Card dealerCard = dealer.getCards().get(0);
        System.out.printf("%s: %s%n", dealer.getName(), PrintConverter.of(dealerCard));
    }

    public static void printCard(Participant participant) {
        System.out.printf("%s카드: %s%n", participant.getName(), getAllCardsNames(participant));
    }

    private static String getAllCardsNames(Participant participant) {
        List<Card> cards = participant.getCards();
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        for (int i = 0; i < cards.size(); i++) {
            stringJoiner.add(PrintConverter.of(cards.get(i)));
        }
        return stringJoiner.toString();
    }

    public static void printHit() {
        System.out.printf("%n딜러는 16이하라 한장의 카드를 더 받았습니다.%n");
    }

    public static void printResults(Dealer dealer, Players players) {
        System.out.println();
        printResult(dealer);
        players.getPlayers().forEach(OutputView::printResult);
    }

    private static void printResult(Participant participant) {
        System.out.printf("%s카드: %s - 결과: %d%n", participant.getName(),
            getAllCardsNames(participant), participant.getSumOfCards());
    }

    public static void printProfits(DealerScore dealerScore, List<PlayerScore> playerScores) {
        printDealerProfit(dealerScore);
        printPlayersProfit(playerScores);
    }

    private static void printDealerProfit(DealerScore dealerScore) {
        System.out.printf("딜러: %f%n", dealerScore.getProfit());
    }

    private static void printPlayersProfit(List<PlayerScore> playerScores) {
        for (PlayerScore result : playerScores) {
            System.out.printf("%s: %f%n", result.getName(), result.getProfit());
        }
    }

    public static void printError(Exception e) {
        System.out.printf("[ERROR] %s%n", e.getMessage());
    }
}
