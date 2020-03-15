package domain.player;

import domain.card.Card;

public class Player extends User {
    private String name;

    public Player(String name, Card... cards) {
        super(cards);
        validatePlayerName(name);
        this.name = name;
    }

    private void validatePlayerName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException("플레이어 이름이 null 입니다.");
        }
    }

    @Override
    public void drawCard(Card card) {
        this.cards.add(card);
        validateDuplicateCard();
    }

    public String getName() {
        return name;
    }
}
