package domain.user;

import java.util.stream.Collectors;

import domain.deck.Card;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    public String getFirstDrawResult() {
        return name + ": "
                + cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }
}
