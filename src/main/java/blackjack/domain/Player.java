package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Card> cards = new ArrayList<>();
    private final String name;

    public Player(final String name) {
        validatePlayerName(name);
        this.name = name;
    }

    private static void validatePlayerName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어명은 공백이 될 수 없습니다.");
        }
    }

    public void drawCard(final Deck deck) {
        cards.add(deck.drawCard());
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
