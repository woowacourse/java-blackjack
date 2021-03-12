package blackjack.domain.player;

import blackjack.domain.card.Deck;

import java.util.Collections;
import java.util.List;

public class Gamblers {
    private final List<Gambler> gamblers;

    public Gamblers(final List<Gambler> gamblers) {
        this.gamblers = gamblers;
    }

    public void initGamblerCards(Deck deck) {
        gamblers.forEach(player -> player.initializeCards(deck));
    }

    public List<Gambler> gamblers() {
        return Collections.unmodifiableList(gamblers);
    }
}
