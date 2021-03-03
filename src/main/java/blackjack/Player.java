package blackjack;

import blackjack.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private final Name name;
    private final List<Card> cards = new ArrayList<>();

    public Player(String inputName) {
        this(new Name(inputName));
    }

    private Player(Name name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateCards() {
        return cards.stream()
                    .mapToInt(Card::getValue)
                    .sum();
    }

    public String getName() {
        return name.toString();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}