package domain.gamer;

import domain.card.Card;
import domain.card.CardNumber;

import java.util.ArrayList;
import java.util.List;

public abstract class Gamer {
    private static final int BUST_NUMBER = 22;
    private static final int ACE_HIDDEN_SCORE = 10;

    private String name;
    private final List<Card> cards = new ArrayList<>();

    public Gamer(String name) {
        this.name = name;
    }

    public abstract boolean isDrawable();

    public void addCard(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int calculateScore() {
        int score = calculateScoreWithoutAce();

        if (isContainAce() && score + ACE_HIDDEN_SCORE < BUST_NUMBER) {
            return score + ACE_HIDDEN_SCORE;
        }

        return score;
    }

    private int calculateScoreWithoutAce() {
        return cards.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getScore)
                .sum();
    }

    public boolean isContainAce() {
        return cards.stream()
                .anyMatch(x -> x.getCardNumber() == CardNumber.ACE);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
