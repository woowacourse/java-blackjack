package domain.gamer;

import domain.card.Card;
import domain.card.CardNumber;

import java.util.ArrayList;
import java.util.List;

public abstract class Gamer {
    private String name;
    protected final List<Card> cards = new ArrayList<>();
    protected Result result;

    public Gamer(String name) {
        this.name = name;
        this.result = new Result();
    }

    public abstract boolean isDrawable();

    public void addCard(List<Card> cards) {
        this.cards.addAll(cards);
        result.calculateScore(this.cards, isContainAce());
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

    public Result getResult() {
        return result;
    }
}
