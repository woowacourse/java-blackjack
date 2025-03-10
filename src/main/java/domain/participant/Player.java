package domain.participant;

import domain.card.CardDeck;
import domain.game.Winning;

public class Player extends Participant {

    private final String name;

    public Player(String name) {
        super(new CardDeck());
        validateNotBlank(name);
        name = name.trim();
        validateLength(name);
        this.name = name;
    }

    private static void validateNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 비어있을 수 없습니다.");
        }
    }

    private static void validateLength(String name) {
        if (name.length() > 10) {
            throw new IllegalArgumentException("플레이어의 이름은 10자를 넘을 수 없습니다.");
        }
    }

    @Override
    public boolean canTakeMoreCard() {
        return (calculateScore() <= Winning.BLACK_JACK);
    }

    public String getName() {
        return name;
    }
}
