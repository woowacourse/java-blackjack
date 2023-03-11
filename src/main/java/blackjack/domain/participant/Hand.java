package blackjack.domain.participant;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    public static final int FIRST_CARD_INDEX = 0;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public Hand add(Card card) {
        ArrayList<Card> newCards = new ArrayList<>(this.cards);
        newCards.add(card);
        return new Hand(newCards);
    }

    public Hand add(Card... cards) {
        ArrayList<Card> newCards = new ArrayList<>(this.cards);
        newCards.addAll(List.of(cards));
        return new Hand(newCards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Score calculateScore() {
        Score score = Score.of(mapCardsToNumbers());
        if (containsAce()) {
            return score.getValueIncludingAce();
        }
        return score;
    }

    private int[] mapCardsToNumbers() {
        return cards.stream().mapToInt(Card::getScore).toArray();
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public Card pickFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }
}
