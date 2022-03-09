package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.LinkedHashSet;

public class Player {

    private final String name;
    private final Cards cards;

    public Player(String name) {
        validateName(name);
        this.name = name;
        this.cards = new Cards(new LinkedHashSet<>());
    }

    private void validateName(String name) {
        validateEmptyName(name);
        validateNameLength(name);
    }

    private void validateEmptyName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("공백은 허용되지 않습니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > 100) {
            throw new IllegalArgumentException("길이는 100자를 초과할 수 없습니다.");
        }
    }

    public void hit(Card card) {
        cards.add(card);
    }

    public int getScore() {
        return cards.sum();
    }
}
