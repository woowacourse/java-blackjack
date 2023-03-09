package domain.player;

import domain.card.CardHolder;

import java.util.List;
import java.util.stream.Collectors;

public class Participant extends Player {
    public Participant(CardHolder cardHolder, Name name) {
        super(cardHolder, name);
    }

    public static List<Participant> from(List<Name> names) {
        return names.stream()
                .map(name -> new Participant(CardHolder.makeEmptyHolder(), name))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isNameEqualTo(String playerName) {
        return getName().equals(playerName);
    }
}
