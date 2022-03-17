package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.HashSet;
import java.util.Set;

public class Player extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final String ERROR_MESSAGE_PROHIBIT_NAME = "플레이어의 이름은 '딜러'일 수 없습니다.";

    public Player(String name, Set<Card> cards) {
        super(name, cards);
        validateProhibitName(name);
    }

    public Player(String name) {
        this(name, new HashSet<>());
    }

    private void validateProhibitName(String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PROHIBIT_NAME);
        }
    }

    @Override
    public boolean hasNextTurn() {
        return !isBust();
    }
}
