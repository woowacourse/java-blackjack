package blackjack.domain.gamer;

import blackjack.domain.card.Cards;

public class Player extends Person {

    public Player(String name) {
        validateNameLength(name);
        super.name = name;
        super.cards = new Cards();
    }

    private void validateNameLength(String name) {
        if (name.length() < 1) {
            throw new IllegalArgumentException("유효하지 않은 플레이어 이름입니다.");
        }
    }
}
