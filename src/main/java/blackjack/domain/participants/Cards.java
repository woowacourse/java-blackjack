package blackjack.domain.participants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.controller.BlackJack;
import blackjack.domain.card.Card;

public class Cards {
    public static final int GAP_BETWEEN_ACE_VALUE = 10;
    public static final String DELIMITER = ", ";

    private List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    // 테스트용
    Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public int addedCardCount() {
        return cards.size() - Participants.FIRST_CARDS_COUNT;
    }

    public int calculate() {
        int total = cards.stream()
            .mapToInt(Card::getRankValue)
            .reduce(0, Integer::sum);
        return reduceIfAceExists(total);
    }

    private int reduceIfAceExists(int total) {
        int aceCount = countAce();
        while (total > BlackJack.BLACK_JACK_SCORE && aceCount > 0) {
            total -= GAP_BETWEEN_ACE_VALUE;
            aceCount--;
        }
        return total;
    }

    private int countAce() {
        return Math.toIntExact(
            cards.stream()
                .filter(Card::isAce)
                .count()
        );
    }

    @Override
    public String toString() {
        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.joining(DELIMITER));
    }
}
