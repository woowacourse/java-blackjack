package model.paticipant;

import java.util.List;
import java.util.function.Consumer;

public record Players<T extends Player>(List<T> players) {

    public Players(List<T> players) {
        this.players = List.copyOf(players);
    }

    public void forEach(Consumer<? super T> action) {
        players.forEach(action);
    }
}
