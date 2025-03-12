package domain.participant;

import domain.card.CardHand;

public class Player extends AbstractGambler {

    public static final int TAKE_CARD_THRESHOLD = 21;
    public static final int NAME_LENGTH_THRESHOLD = 10;

    public Player(String name, CardHand cardHand) {
        super(name, cardHand);
        validateNotBlank(name);
        validateLength(name);
    }

    @Override
    public boolean canTakeMoreCard() {
        return (calculateScore() <= TAKE_CARD_THRESHOLD);
    }

    private void validateLength(String name) {
        if (name.length() > NAME_LENGTH_THRESHOLD) {
            throw new IllegalArgumentException("플레이어의 이름은 10자를 넘을 수 없습니다.");
        }
    }

    private void validateNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 비어있을 수 없습니다.");
        }
    }
}
