package blackjack.domain.player;

import blackjack.domain.card.CardDeck;
import java.util.List;
import java.util.stream.Collectors;

public class Gamblers {

    private final List<Gambler> gamblers;

    private Gamblers(List<Gambler> gamblers) {
        this.gamblers = gamblers;
    }

    public static Gamblers of(List<Gambler> gamblers) {
        return new Gamblers(gamblers);
    }

    public int size() {
        return gamblers.size();
    }

    public void distributeCard(CardDeck cardDeck) {
        for (Gambler gambler : gamblers) {
            gambler.addCard(cardDeck.draw());
        }
    }

    public List<Gambler> getCardNeedGamblers() {
        return gamblers.stream()
            .filter(Gambler::isOneMoreCard)
            .collect(Collectors.toList());
    }

    public List<String> getGamblerNames() {
        return gamblers.stream()
            .map(Gambler::getName)
            .collect(Collectors.toList());
    }

    public List<Gambler> getGamblers() {
        return List.copyOf(gamblers);
    }
}
