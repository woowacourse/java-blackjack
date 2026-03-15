package model.paticipant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public record Players(List<Player> players) {

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    @Override
    public List<Player> players() {
        return Collections.unmodifiableList(players);
    }

    public void forEach(Consumer<? super Player> action) {
        players.forEach(action);
    }
}
