package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements Person {
    private final Name name;
    private final List<Card> cards;

    public Player(Name name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> showCards() {
        return null;
    }

    @Override
    public boolean isHit() {
        int totalScore = cards.stream()
                .map(card -> Collections.min(card.getScore()))
                .reduce(0, Integer::sum);
        return totalScore < 21;
    }

    @Override
    public int calculateScore() {
        return 0;
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
