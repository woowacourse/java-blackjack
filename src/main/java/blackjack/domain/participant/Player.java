package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import java.util.Collections;
import java.util.Set;

public class Player extends Participant {

    private static final String BLACK_NAME_INPUT_EXCEPTION_MESSAGE = "플레이어는 이름을 지녀야 합니다.";
    private static final String INVALID_PLAYER_NAME_EXCEPTION_MESSAGE = "플레이어의 이름은 딜러가 될 수 없습니다.";

    private final String name;

    private Player(final String name, final CardBundle cardBundle) {
        super(cardBundle);
        this.name = name;
    }

    public static Player of(final String name, final CardBundle cardBundle) {
        validateName(name);
        return new Player(name, cardBundle);
    }

    private static void validateName(String name) {
        validateNameExistence(name);
        validateNameNotDealer(name);
    }

    private static void validateNameExistence(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(BLACK_NAME_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private static void validateNameNotDealer(String name) {
        if (name.equals(Dealer.UNIQUE_NAME)) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public boolean canDraw() {
        return !isBust();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<Card> getInitialOpenCards() {
       return Collections.unmodifiableSet(cardBundle.getCards());
    }

    @Override
    public String toString() {
        return "Player{" +
                "cardBundle=" + cardBundle +
                ", name='" + name + '\'' +
                '}';
    }
}
