package domain.player;

import domain.card.CardHolder;

public class Participant extends Player {
    public Participant(CardHolder cardHolder, Name name) {
        super(cardHolder, name);
    }

    @Override
    public boolean isNameEqualTo(String playerName) {
        return getName().equals(playerName);
    }
}
