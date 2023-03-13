package view;

import domain.card.Card;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ",";

    public static void printStart(Dealer dealer, Players players) {
        printGiveMessage(dealer, players);
        printHideCard(dealer);
        players.forEach(OutputView::printCard);
        System.out.println();
    }

    private static void printGiveMessage(Dealer dealer, Players players) {
        String playersNames = players.stream()
            .map(Participant::getName)
            .collect(Collectors.joining(","));
        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), playersNames);
    }

    private static void printHideCard(Dealer dealer) {
        Card dealerCard = dealer.getFirstCard();
        System.out.printf("%s: %s%n", dealer.getName(), CardConverter.of(dealerCard));
    }

    public static void printCard(Participant participant) {
        System.out.printf("%s카드: %s%n", participant.getName(), getAllCardsNames(participant));
    }

    private static String getAllCardsNames(Participant participant) {
        Cards cards = participant.getCards();
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        for (Card card : cards) {
            stringJoiner.add(CardConverter.of(card));
        }
        return stringJoiner.toString();
    }

    public static void printHit() {
        System.out.printf("%n딜러는 16이하라 한장의 카드를 더 받았습니다.%n");
    }

    public static void printResults(Dealer dealer, Players players) {
        System.out.println();
        printResult(dealer);
        players.forEach(OutputView::printResult);
    }

    private static void printResult(Participant participant) {
        System.out.printf("%s카드: %s - 결과: %d%n", participant.getName(),
            getAllCardsNames(participant), participant.getCards().getScore());
    }

    public static void printError(Exception e) {
        System.out.printf("[ERROR] %s%n", e.getMessage());
    }

    public static void printGains(Gain dealerGain, Gains playerGaines) {
        System.out.println("\n## 최종 수익");
        printGain(dealerGain);
        playerGaines.forEach(OutputView::printGain);
    }

    private static void printGain(Gain gain) {
        System.out.printf("%s: %.0f%n", gain.getName(), gain.getBetAmount());
    }
}
