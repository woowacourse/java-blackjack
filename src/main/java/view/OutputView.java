package view;

import domain.card.Card;
import domain.card.Cards;
import domain.participant.Dealer;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ",";

    public static void printStart(Card firstDealerCard, List<String> playerNames,
        List<Cards> playerCards) {
        printGiveMessage(playerNames);
        printHideCard(firstDealerCard);
        for (int i = 0; i < playerNames.size(); i++) {
            printCard(playerNames.get(i), playerCards.get(i));
        }
        System.out.println();
    }

    private static void printGiveMessage(List<String> playerNames) {
        String result = String.join(",", playerNames);
        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", Dealer.NAME, result);
    }

    private static void printHideCard(Card firstDealerCard) {
        System.out.printf("%s: %s%n", Dealer.NAME, CardConverter.of(firstDealerCard));
    }

    public static void printCard(String participantName, Cards cards) {
        System.out.printf("%s카드: %s%n", participantName, getAllCardsNames(cards));
    }

    private static String getAllCardsNames(Cards cards) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        for (Card card : cards) {
            stringJoiner.add(CardConverter.of(card));
        }
        return stringJoiner.toString();
    }

    public static void printHit() {
        System.out.printf("%n%s는 16이하라 한장의 카드를 더 받았습니다.%n", Dealer.NAME);
    }

    public static void printResults(Cards dealerCards, List<String> playerNames,
        List<Cards> playerCards) {
        System.out.println();
        printResult(Dealer.NAME, dealerCards);
        for (int i = 0; i < playerNames.size(); i++) {
            printResult(playerNames.get(i), playerCards.get(i));
        }
    }

    private static void printResult(String participantName, Cards participantCards) {
        System.out.printf("%s카드: %s - 결과: %d%n", participantName,
            getAllCardsNames(participantCards), participantCards.getScore());
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
