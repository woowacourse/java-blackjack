package model.user.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import model.card.Card;

import static controller.BlackJackGame.*;

public class CardHand implements Iterable<Card> {
    private static final int ADDITIONAL_ACE_SCORE = 10;
    private static final int BLACK_JACK_COUNT = 21;

    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        if (isAce()) {
            return calculateScoreWithAce();
        }
        return calculateScoreWithNoAce();
    }

    private int calculateScoreWithAce() {
        int score = calculateScoreWithNoAce();
        if (score + ADDITIONAL_ACE_SCORE > BLACK_JACK_COUNT) {
            return score;
        }
        return score + ADDITIONAL_ACE_SCORE;
    }

    private int calculateScoreWithNoAce() {
        return cards.stream()
            .mapToInt(Card::calculateScore)
            .sum();
    }

    public boolean isAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return calculateScore() > BLACK_JACK_COUNT;
    }

    public boolean isBlackJack() {
        return calculateScore() == BLACK_JACK_COUNT && cards.size() == INITIAL_DRAW_COUNT;
    }

    public boolean isMoreThanBlackJack() {
        return calculateScore() >= BLACK_JACK_COUNT;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public String toString() {
        List<String> cardNames = cards.stream()
            .map(Card::toString)
            .collect(Collectors.toList());
        return String.join(COMMA, cardNames);
    }
}