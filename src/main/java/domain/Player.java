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

    public void saveCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return cards.stream()
                .map(Card::getScore)
                .mapToInt(score -> score)
                .sum();
    }

    public boolean isBlackJack(final int blackJackScore) {
        return this.calculateScore() == blackJackScore;
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
