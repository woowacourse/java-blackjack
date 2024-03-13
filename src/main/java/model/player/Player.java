package model.player;

import java.util.List;
import model.card.Card;
import model.card.Cards;

public class Player {

    private static final int HIT_CONDITION = 22;

    private final String name;
    private Cards cards;

    public Player(String name) {
        this(name, new Cards(List.of()));
    }

    public Player(String name, Cards cards) {
        validateEmptyName(name);
        this.name = name;
        this.cards = cards;
    }

    private void validateEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("플레이어 이름은 빈 값일 수 없습니다.");
        }
    }

    public boolean isPossibleHit() {
        int totalNumbers = cards.calculateTotalNumbers();
        return totalNumbers < HIT_CONDITION;
    }

    public void hitCard(Card card) {
        cards = cards.add(card);
    }

    public int cardsSize() {
        return cards.size();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
