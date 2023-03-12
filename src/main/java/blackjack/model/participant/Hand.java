package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    protected static final int BLACKJACK_NUMBER = 21;
    protected static final int BONUS_SCORE = 10;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = cards.stream()
                .mapToInt(card -> card.getNumber().getScore())
                .sum();
        if (hasAceCard() && isNotBustEvenIfTakeBonusScore(score)) {
            return score + BONUS_SCORE;
        }
        return score;
    }

    private boolean hasAceCard() {
        return cards.stream()
                .map(Card::getNumber)
                .anyMatch(cardNumber -> cardNumber.equals(CardNumber.ACE));
    }

    private boolean isNotBustEvenIfTakeBonusScore(int score) {
        return score + BONUS_SCORE <= BLACKJACK_NUMBER;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
