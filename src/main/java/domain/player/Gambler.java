package domain.player;

import domain.card.Hand;

import java.util.List;
import java.util.stream.Collectors;

public class Gambler extends Player {
    public Gambler(Hand hand, Name name) {
        super(hand, name);
    }

    public static List<Gambler> from(List<Name> names) {
        return names.stream()
                .map(name -> new Gambler(Hand.makeEmptyHolder(), name))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isNameEqualTo(String playerName) {
        return getName().equals(playerName);
    }
}
