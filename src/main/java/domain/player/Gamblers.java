package domain.player;

import domain.card.CardDeck;
import java.util.List;
import java.util.Objects;

public class Gamblers {
    private static final int MAXIMUM_VALID_NUMBER_OF_GAMBLERS = 7;
    private static final String ERROR_NULL_OR_EMPTY_GAMBLERS = "[ERROR] 겜블러 목록을 확인해주세요";
    private static final String ERROR_TOO_MANY_GAMBLERS = "[ERROR] 겜블러는 최대 7명까지 참여 가능합니다";

    private final List<Gambler> gamblers;

    public Gamblers(List<Gambler> gamblers) {
        this.gamblers = List.copyOf(gamblers);
        validateEmpty(gamblers);
        validateNumberOfGamblers(gamblers);
    }

    private void validateEmpty(List<Gambler> gamblers) {
        if (Objects.isNull(gamblers) || gamblers.isEmpty()) {
            throw new IllegalArgumentException(ERROR_NULL_OR_EMPTY_GAMBLERS);
        }
    }

    private void validateNumberOfGamblers(List<Gambler> gamblers) {
        if (gamblers.size() > MAXIMUM_VALID_NUMBER_OF_GAMBLERS) {
            throw new IllegalArgumentException(ERROR_TOO_MANY_GAMBLERS);
        }
    }

    public void addCard(CardDeck cardDeck) {
        for (Gambler gambler : this.gamblers) {
            gambler.addCard(cardDeck.drawCard());
        }
    }

    public List<Gambler> getGamblers() {
        return gamblers;
    }
}
