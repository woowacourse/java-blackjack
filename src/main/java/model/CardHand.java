package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardHand {
    private List<Card> cards = new ArrayList<>();


    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCard(Strategy strategy) {
        cards.add(strategy.draw());
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
        if (score + 10 > 21) {
            return score;
        }
        return score + 10;
    }

    public int calculateScoreWithNoAce() {
        return cards.stream()
                .map(Card::getSymbol)
                .map(Symbol::getScore)
                .reduce((integer, integer2) -> integer+integer2)
                .get();
    }

    public boolean isAce() {
         return cards.stream().filter(Card::isAce).count() > 0;
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }
}
