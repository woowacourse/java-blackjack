package domain.gamer;

import domain.card.Card;
import domain.card.CardNumber;

import java.util.ArrayList;
import java.util.List;

public abstract class Gamer {
    protected String name;
    protected final List<Card> cards = new ArrayList<>();

    public Gamer(String name) {
        this.name = name;
    }

    public void addCard(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public abstract boolean isDrawable();

    public static final int BUST_NUMBER = 22;
    public static final int ACE_HIDDEN_SCORE = 10;

    public int calculateWithAce() {
        int score = calculateScore();
        if (isContainAce() && score + ACE_HIDDEN_SCORE < BUST_NUMBER) {
            return score + ACE_HIDDEN_SCORE;
        }
        return score;
    }

    private int calculateScore() {
        return cards
                .stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getScore)
                .sum();
    }

    public String getName() {
        return name;
    }

    public boolean isContainAce() {
        return cards.stream().anyMatch(x -> x.getCardNumber() == CardNumber.ACE);
    }

    public List<Card> getCards() {
        return cards;
    }
}
