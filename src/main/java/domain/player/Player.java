package domain.player;

import domain.CardCalculator;
import domain.card.Card;
import domain.card.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player {
    protected List<Card> cards;

    public Player(Card... cards) {
        this.cards = new ArrayList<>();
        Collections.addAll(this.cards, cards);
    }

    protected int sumCardNumber() {
        return CardCalculator.calculateCards(this.cards);
    }

    abstract void insertCard(Cards cards);

    public List<Card> getCard() {
        return Collections.unmodifiableList(this.cards);
    }
}
