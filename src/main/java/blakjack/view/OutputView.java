package blakjack.view;

import blakjack.domain.card.Card;
import blakjack.domain.participant.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한 장의 카드를 더 받았습니다.";
    private static final String DELIMITER = ", ";

    private OutputView() {
    }

    public static void printInitCards(final Participant dealer, final List<Participant> players) {
        final List<String> playerNames = players.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
        printNames(dealer.getName(), playerNames);
        printCards(dealer, players);
    }

    private static void printNames(final String dealerName, final List<String> playerNames) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealerName, String.join(DELIMITER, playerNames));
    }

    private static void printCards(final Participant dealer, final List<Participant> players) {
        System.out.printf("%s: %s%n", dealer.getName(), convertFirstCard(dealer.getCards()));
        for (final Participant player : players) {
            System.out.printf("%s: %s%n", player.getName(), convertCardsToString(player.getCards()));
        }
    }

    private static String convertFirstCard(final List<Card> dealerCards) {
        final Card firstCard = dealerCards.get(0);
        return firstCard.getFace() + firstCard.getSuit();
    }

    private static String convertCardsToString(final List<Card> cards) {
        final List<String> convertedCards = new ArrayList<>();
        for (final Card card : cards) {
            convertedCards.add(card.getFace() + card.getSuit());
        }
        return String.join(DELIMITER, convertedCards);
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printDealerHit() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public static void printDealerCards(final Participant player) {
        System.out.printf("%s카드: %s%n", player.getName(), convertCardsToString(player.getCards()));
    }

    public static void printScore(final Participant dealer, final List<Participant> players) {
        System.out.printf("%s 카드: %s - 결과: %d%n", dealer.getName(), convertCardsToString(dealer.getCards()), dealer.getTotalScore());
        for (final Participant player : players) {
            System.out.printf("%s 카드: %s - 결과: %d%n", player.getName(), convertCardsToString(player.getCards()), player.getTotalScore());
        }
    }
}
