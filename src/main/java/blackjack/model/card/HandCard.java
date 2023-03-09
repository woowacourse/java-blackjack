package blackjack.model.card;

import blackjack.model.ResultState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HandCard {
    public static final int BUST_THROTTLE = 21;

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

    public CardScore score(ResultState state) {
        int score = bigScore();
        if (isBigScoreOver(BUST_THROTTLE)) {
            score = smallScore();
        }
        return new CardScore(score, state);
    }

    public boolean isBigScoreEqual(int throttle) {
        return (bigScore() == throttle);
    }

    public boolean isBigScoreOver(int throttle) {
        return (bigScore() > throttle);
    }

    public boolean isSmallScoreOver(int throttle) {
        return (smallScore() > throttle);
    }

    public int smallScore() {
        int score = 0;
        for (CardNumber number : this.getCardNumbers()) {
            score += number.getSmallScore();
        }
        return score;
    }

    public int bigScore() {
        int score = 0;
        for (CardNumber number : this.getCardNumbers()) {
            score += number.getBigScore();
        }
        return score;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    private List<CardNumber> getCardNumbers() {
        return cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toList());
    }
}
