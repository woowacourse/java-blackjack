package domain.player;

import domain.deck.CardDeck;
import expcetion.BlackjackException;
import expcetion.ExceptionMessage;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Gamblers {

    private final List<Gambler> gamblers;

    public Gamblers(List<Gambler> gamblers) {
        validateNonDuplicate(gamblers);
        this.gamblers = gamblers.stream().toList();
    }

    private void validateNonDuplicate(List<Gambler> gamblers) {
        Set<String> names = gamblers.stream()
                .map(Gambler::getName)
                .collect(Collectors.toSet());

        if (names.size() != gamblers.size()) {
            throw new BlackjackException(ExceptionMessage.NAME_DUPLICATE_ERROR);
        }
    }

    public void dealAll(CardDeck cardDeck) {
        gamblers.forEach(gambler -> gambler.deal(cardDeck));
    }

    public List<String> getNames() {
        return gamblers.stream()
                .map(Gambler::getName)
                .toList();
    }

    public void forEachGambler(Consumer<Gambler> consumer) {
        gamblers.forEach(consumer);
    }

}
