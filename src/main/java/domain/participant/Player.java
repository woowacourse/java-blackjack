package domain.participant;

import domain.PlayerCommand;
import domain.card.Card;
import domain.card.Deck;
import java.util.List;

public class Player extends Participant {

    public Player(final Name name, final List<Card> cards) {
        super(name, cards);
    }

    public void receiveAdditionalCard(final PlayerCommand command, final Deck deck) {
        if(command.isHit()){
            this.receiveCard(deck.getCard());
        }
    }
}
