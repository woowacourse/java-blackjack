package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    public static final int BLACKJACK_NUMBER = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;
    private final List<Card> cards;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void draw(Card card) {
        cards.add(card);
    }

    public void initialDraw(Deck deck) {
        cards.add(deck.draw());
        cards.add(deck.draw());
    }

    public boolean isBlackJack() {
        return score() == BLACKJACK_NUMBER && cards.size() == BLACKJACK_CARD_COUNT;
    }

    public boolean isBust() {
        return score() > BLACKJACK_NUMBER;
    }

    public int score() {
        int score = cards.stream()
            .filter(Card::isNotAce)
            .mapToInt(Card::denominationValue)
            .sum();

        if (containAceCard()) {
            return calculateAceScore(score);
        }
        return score;
    }

    private int calculateAceScore(int score) {
        int aceOneScore = Denomination.ACE.getNumber();
        int aceElevenScore = Denomination.ACE_ELEVEN.getNumber();

        if (score + aceElevenScore <= BLACKJACK_NUMBER) {
            return score + aceElevenScore;
        }
        return score + aceOneScore;
    }

    private boolean containAceCard() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public Card firstCard() {
        return cards.get(0);
    }

    public int size() {
        return cards.size();
    }

}
