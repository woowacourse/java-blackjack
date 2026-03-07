package domain.player;

import domain.card.GameCards;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Gamblers {

    private List<Gambler> gamblers;

    public Gamblers(List<String> names) {
        this.gamblers = names.stream()
                .map(Gambler::new)
                .collect(Collectors.toList());
    }

    public boolean containGambler(String name) {
        return gamblers.stream()
                .anyMatch(gambler -> gambler.isEqualName(name));
    }

    public List<Gambler> getGamblersInfo() {
        return Collections.unmodifiableList(gamblers);
    }

    public void receiveCards(GameCards gameCards) {
        for (Gambler gambler : gamblers) {
            gambler.addCard(gameCards.drawCard());
        }
    }
}
