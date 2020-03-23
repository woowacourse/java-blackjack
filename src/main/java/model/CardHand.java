package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static controller.BlackJackGame.BLACK_JACK_COUNT;
import static controller.BlackJackGame.INITIAL_DRAW_COUNT;

public class CardHand implements Iterable<Card> {
    private static final int ADDITIONAL_ACE_SCORE = 10;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        if (hasAce()) {
            return calculateScoreWithAce();
        }
        return calculateScoreWithNoAce();
    }

    public boolean isEmpty() {
        return cards.size() == 0;
    }

    public int calculateScoreWithAce() {
        int score = calculateScoreWithNoAce();
        if (score + ADDITIONAL_ACE_SCORE > BLACK_JACK_COUNT) {
            return score;
        }
        return score + ADDITIONAL_ACE_SCORE;
    }

    public int calculateScoreWithNoAce() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return getScore() > BLACK_JACK_COUNT;
    }

    public boolean isBlackJack() {
        return (getScore() == BLACK_JACK_COUNT) && (cards.size() == INITIAL_DRAW_COUNT);
    }

    public boolean isLowerThanBlackJack() {
        return getScore() < BLACK_JACK_COUNT;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
