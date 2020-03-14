package domain.player;

import domain.CardCalculator;
import domain.card.Card;
import domain.card.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Player {
    private static final String DELIMITER = ", ";
    protected List<Card> cards;
    protected String name;

    public Player(String name, Card... cards) {
        this.cards = new ArrayList<>();
        Collections.addAll(this.cards, cards);
        this.name = name;
    }

    public int sumCardNumber() {
        return CardCalculator.calculateContainAce(this.cards);
    }

    public List<Card> getCard() {
        return Collections.unmodifiableList(this.cards);
    }

    public String getName() {
        return this.name;
    }

    public String getCardNumber() {
        return this.cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(DELIMITER));
    }

    public abstract void insertCard(Cards cards);
}
