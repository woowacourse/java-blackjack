package domain.participant;

import domain.card.HandCards;

public class Player extends Participant {

    private final String name;

    public Player(String name) {
        super(new HandCards());
        validateName(name);
        this.name = name.trim();
    }

    private static void validateName(String name) {
        validateNotBlank(name);
        validateLength(name.trim());
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
        return (calculateScore() <= HandCards.BLACKJACK_SCORE);
    }

    public String getName() {
        return name;
    }
}
