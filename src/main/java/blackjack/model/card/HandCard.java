package blackjack.model.card;

import blackjack.model.ResultState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HandCard {
    public static final int BUST_THROTTLE = 21;
    public static final int ACE_ADDITIONAL_SCORE = 10;
    public static final int NOT_EXIST = 0;

    private final List<Card> cards;

    public HandCard() {
        this.cards = new ArrayList<>();
    }

    public HandCard(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public CardScore cardScore(ResultState state) {
        int score = smallScore();
        long aceCount = aceCount();
        while ((score + ACE_ADDITIONAL_SCORE <= BUST_THROTTLE) && (aceCount > NOT_EXIST)) {
            score += ACE_ADDITIONAL_SCORE;
            aceCount--;
        }
        return new CardScore(score, state);
    }

    public int getValidScore() {
        int score = smallScore();
        long aceCount = aceCount();
        while ((score + ACE_ADDITIONAL_SCORE <= BUST_THROTTLE) && (aceCount > NOT_EXIST)) {
            score += ACE_ADDITIONAL_SCORE;
            aceCount--;
        }
        return score;
    }

    public boolean isScoreOver(int throttle) {
        return getValidScore() > throttle;
    }

    public boolean isScoreEqual(int throttle) {
        return getValidScore() == throttle;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int size() {
        return cards.size();
    }

    private int smallScore() {
        int score = 0;
        for (CardNumber number : this.getCardNumbers()) {
            score += number.getSmallScore();
        }
        return score;
    }

    private List<CardNumber> getCardNumbers() {
        return cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toList());
    }

    private long aceCount() {
        return cards.stream()
                .filter(card -> card.getNumber() == CardNumber.ACE)
                .count();
    }
}
