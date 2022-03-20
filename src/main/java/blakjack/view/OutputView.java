package blakjack.view;

import blakjack.domain.card.Card;
import blakjack.domain.participant.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printInitCards(final Participant dealer, final List<Participant> players) {
        final List<String> playerNames = players.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
        printNames(dealer.getName(), playerNames);
        printCards(dealer, players);
    }

    private static void printCards(final Participant dealer, final List<Participant> players) {
        System.out.printf("%s: %s%n", dealer.getName(), convertFirstCard(dealer.getCards()));
        for (final Participant player : players) {
            System.out.printf("%s: %s%n", player.getName(), convertToString(player.getCards()));
        }
    }

    private static String convertFirstCard(final List<Card> dealerCards) {
        final Card firstCard = dealerCards.get(0);
        return firstCard.getFace() + firstCard.getSuit();
    }

    private static String convertToString(final List<Card> cards) {
        final List<String> convertedCards = new ArrayList<>();
        for (final Card card : cards) {
            convertedCards.add(card.getFace() + card.getSuit());
        }
        return String.join(", ", convertedCards);
    }

    private static void printNames(final String dealerName, final List<String> playerNames) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealerName, String.join(", ", playerNames));
    }
}
