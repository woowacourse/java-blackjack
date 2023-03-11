package ui.output;

import java.util.LinkedHashMap;
import java.util.Map;
import model.money.Bet;
import model.user.Player;

public class PurseResponse {

    private final Map<String, Long> purses;

    public PurseResponse(Map<String, Long> purses) {
        this.purses = purses;
    }

    public static PurseResponse create(Map<Player, Bet> purses) {
        Map<String, Long> response = new LinkedHashMap<>();
        for (Player player : purses.keySet()) {
            response.put(player.getName(), purses.get(player).getMoney());
        }

        return new PurseResponse(response);
    }

    public Map<String, Long> getPurses() {
        return purses;
    }
}
