package domain;

import java.util.Collections;
import java.util.List;

import domain.enums.MatchCase;

public class Player {

    private final Cards cards;
    private final String name;
    private final Betting betting;

    public Player(String name, Betting betting) {
        this.name = name;
        this.betting = betting;
        this.cards = new Cards();
    }

    public void add(Card card) {
        cards.addCard(card);
    }

    public void addInitializedCard(Deck deck) {
        add(deck.pop());
        add(deck.pop());
    }

    public void calculateMoney(MatchCase matchCase, boolean isDealerBlackjack) {
        betting.calculateMoney(matchCase, cards.isBlackjack(), isDealerBlackjack);
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    public List<String> getCardsDisplay() {
        return Collections.unmodifiableList(cards.getCardsDisplay());
    }

    public int getBettingScore() {
        return betting.getBettingScore();
    }

    public int getCardsTotalSum() {
        return cards.getFinalScore();
    }

}
