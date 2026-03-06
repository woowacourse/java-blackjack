package domain.player;

import domain.card.GameCards;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Gamblers {

    private List<Player> gamblers;

    public Gamblers(List<String> names) {
        this.gamblers = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public boolean containGambler(String name) {
        return gamblers.stream()
                .anyMatch(player -> player.isEqualName(name));
    }

    public List<Player> getGamblersInfo() {
        return Collections.unmodifiableList(gamblers);
    }

    public void receiveCards(GameCards gameCards) {
        for (Player player : gamblers) {
            player.addCard(gameCards.drawCard());
        }
    }
}
