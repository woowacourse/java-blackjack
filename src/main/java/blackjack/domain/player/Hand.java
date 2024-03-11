package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.rule.Score;
import java.util.List;

public class Hand {

    private static final int INITIAL_HAND_SIZE = 2;
    private static final int BLACK_JACK = 21;
    private static final int ACE_WEIGHT = 10;

    private final List<Card> cards;

    Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand createHandFrom(CardDeck cardDeck) {
        return new Hand(cardDeck.popCards(INITIAL_HAND_SIZE));
    }

    public int calculateCardSummation() {
        return cards.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getValue)
                .sum();
    }

    public void appendCard(Card card) {
        cards.add(card);
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public int countPop() {
        return cards.size() - INITIAL_HAND_SIZE;
    }

    public Score calculateScore() {
        int aceCount = countAce();
        int sum = calculateCardSummation();
        while (aceCount > 0 && (sum + ACE_WEIGHT) <= BLACK_JACK) {
            sum += ACE_WEIGHT;
            aceCount--;
        }
        return new Score(sum);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
