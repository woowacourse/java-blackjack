package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardHand  {
    public static final int ADDITIONAL_ACE_SCORE = 10;
    public static final int BLACK_JACK_SCORE = 21;

    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int calculateScore() {
        if (isAce()) {
            return calculateScoreWithAce();
        }
        return calculateScoreWithNoAce();
    }

    public int calculateScoreWithAce() {
        int score = calculateScoreWithNoAce();
        if (score + ADDITIONAL_ACE_SCORE > BLACK_JACK_SCORE) {
            return score;
        }
        return score + ADDITIONAL_ACE_SCORE;
    }

    public int calculateScoreWithNoAce() {
        return cards.stream()
                .map(Card::getSymbol)
                .map(Symbol::getScore)
                .reduce((integer, integer2) -> integer+integer2)
                .get();
    }

    public boolean isAce() {
         return cards.stream().anyMatch(Card::isAce);
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }
}
