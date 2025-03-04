package blackjack.domain;

import blackjack.domain.card.Card;

public class Player {
    private final String name;
    private final Hands hands = new Hands();

    public Player(final String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String validateName(String name) {
        if (name.length() < 2 || name.length() > 5) {
            throw new IllegalArgumentException("[ERROR]");
        }
        if (name.split(" ").length != 1) {
            throw new IllegalArgumentException("[ERROR]");
        }
        return name;
    }

    public void addCards(final Card card) {
        hands.addNewCard(card);
    }

    public int calculateSum() {
        return hands.calculateSum();
    }
}
