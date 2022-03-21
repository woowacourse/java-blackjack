package domain.participant;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class Players implements Iterable<Player> {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = List.copyOf(players);
    }

    public boolean isNotAllBust() {
        long bustPlayerCount = players.stream()
                .filter(Player::isBust)
                .count();
        return bustPlayerCount != players.size();
    }

    public List<Name> getNames() {
        return players.stream().map(Player::getName).collect(Collectors.toList());
    }

    @Override
    public void forEach(Consumer<? super Player> action) {
        Iterable.super.forEach(action);
    }

    @NotNull
    @Override
    public Iterator<Player> iterator() {
        return new PlayersIterator();
    }

    public class PlayersIterator implements Iterator<Player> {

        private int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex < players.size();
        }

        @Override
        public Player next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return players.get(currentIndex++);
        }
    }
}
