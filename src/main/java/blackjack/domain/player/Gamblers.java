package blackjack.domain.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Gamblers implements Iterable<Gambler> {
    private final List<Gambler> gamblers;

    public Gamblers(final List<Gambler> gamblers) {
        this.gamblers = new ArrayList<>(gamblers);
    }

    @Override
    public Iterator<Gambler> iterator() {
        return gamblers.iterator();
    }
}
