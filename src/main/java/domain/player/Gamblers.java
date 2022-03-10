package domain.player;

import domain.card.CardDeck;
import java.util.List;
import java.util.Objects;

public class Gamblers {
    public static final String ERROR_NULL_OR_EMPTY_GAMBLERS = "[ERROR] 겜블러 목록을 확인해주세요";
    private final List<Gambler> gamblers;

    public Gamblers(List<Gambler> gamblers) {
        validateNullAndEmpty(gamblers);
        this.gamblers = List.copyOf(gamblers);
    }

    private void validateNullAndEmpty(List<Gambler> gamblers) {
        if (Objects.isNull(gamblers) || gamblers.isEmpty()) {
            throw new IllegalArgumentException(ERROR_NULL_OR_EMPTY_GAMBLERS);
        }
    }

    public List<Gambler> getGamblers() {
        return gamblers;
    }

    public void addCardForEach(CardDeck cardDeck) {
        gamblers.forEach(
                gambler -> gambler.addCard(cardDeck.getCard())
        );
    }
}
