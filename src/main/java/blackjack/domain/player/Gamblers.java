package blackjack.domain.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Gamblers implements Iterable<Gambler> {
    private final List<Gambler> gamblers;

    public Gamblers(final List<Gambler> gamblers) {
        this.gamblers = new ArrayList<>(gamblers);
    }

    public List<String> getNames(){
        return gamblers.stream()
                .map(gambler -> gambler.getNameValue())
                .collect(Collectors.toList());
    }

    public List<Gambler> getGamblers(){
        return new ArrayList<>(gamblers);
    }

    @Override
    public Iterator<Gambler> iterator() {
        return gamblers.iterator();
    }
}
