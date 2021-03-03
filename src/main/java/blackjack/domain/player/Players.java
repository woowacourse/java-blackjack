package blackjack.domain.player;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Players implements Iterable<Player>{
    private final List<Player> players;

    public Players(List<Player> players){
        this.players = players;
    }

    public List<Player> players(){
        return Collections.unmodifiableList(players);
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
