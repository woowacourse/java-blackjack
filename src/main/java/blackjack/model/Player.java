package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    protected final String name;
    protected final List<Deck> cards;

    public Player(final String name) {
        validateNullAndEmptyName(name);
        this.name = name;
        this.cards = new ArrayList<>();
    }

    private void validateNullAndEmptyName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백을 사용할 수 없습니다.");
        }
    }

    public void receiveCard(final Deck card) {
        cards.add(card);
    }

    public int calculateScore() {
        int sum = cards.stream()
                .mapToInt(Deck::getScore)
                .sum();

        int aceCount = (int) cards.stream()
                .filter(Deck::isAce)
                .count();

        if (aceCount >= 2) {
            sum -= aceCount * 10;
        }

        return sum;
    }

    public String getName() {
        return name;
    }

    public List<Deck> getCards() {
        return List.copyOf(cards);
    }
}
