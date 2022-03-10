package domain.player;

import static java.util.stream.Collectors.joining;

import domain.card.CardDeck;
import java.util.List;
import java.util.Objects;

public class Gamblers {
    private static final String ERROR_NULL_OR_EMPTY_GAMBLERS = "[ERROR] 겜블러 목록을 확인해주세요";
    private static final String GAMBLER_NAMES_JOIN_CHARACTER = ", ";

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

    public String getGamblerNames() {
        return gamblers.stream()
                .map(Gambler::getName)
                .collect(joining(GAMBLER_NAMES_JOIN_CHARACTER));
    }
}
