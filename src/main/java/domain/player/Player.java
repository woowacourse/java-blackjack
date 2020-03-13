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

    public int sumCardNumber() {
        return CardCalculator.calculateContainAce(this.cards);
    }

    public List<Card> getCard() {
        return Collections.unmodifiableList(this.cards);
    }

    public abstract void insertCard(Cards cards);
}
