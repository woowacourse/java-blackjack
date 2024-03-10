package domain.participant;

import domain.constants.CardCommand;

public class Player extends Participant {
    public Player(final String name) {
        super(name);
    }

    @Override
    public boolean canPickCard(CardCommand cardCommand) {
        return CardCommand.HIT.equals(cardCommand) && isNotBusted();
    }
}
