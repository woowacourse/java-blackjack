package domain.player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Gamblers {

    private List<Player> gamblers;

    public Gamblers(List<String> names) {
        this.gamblers = names.stream()
                .map(name -> new Player(name))
                .collect(Collectors.toList());
    }

    public boolean containGambler(String name) {
        return gamblers.stream()
                .anyMatch(player -> player.isEqualName(name));
    }

    public Map<String, List<String>> getGamblersInfo() {
        return gamblers.stream()
                .collect(Collectors.toMap(Player::getName,
                        Player::getCardStatus));
    }
}
