package model.paticipant;

import java.util.List;
import java.util.function.Consumer;

public record Players(List<Player> players) {

    public Players(List<Player> players) {
        this.players = List.copyOf(players);
    }

    public void forEach(Consumer<? super Player> action) {
        players.forEach(action);
    }
}
