package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cards;

    public Player(final String name) {
        validateName(name);
        this.name = name;
        cards = new ArrayList<>();
    }

    private void validateName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public void saveCard(final Card card) {
        cards.add(card);
    }


    public int calculateScore() {
        return cards.stream()
                .map(card -> card.getScore())
                .mapToInt(score -> score)
                .sum();
    }

    public int getTotalSize() {
        return cards.size();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

}
