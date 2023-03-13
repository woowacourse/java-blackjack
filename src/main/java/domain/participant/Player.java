package domain.participant;

import domain.PlayerCommand;
import domain.card.Card;
import domain.card.Deck;
import java.util.List;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(final Name name, final List<Card> cards, final BettingMoney bettingMoney) {
        super(name, cards);
        this.bettingMoney = bettingMoney;
    }

    public void receiveAdditionalCard(final PlayerCommand command, final Deck deck) {
        if(command.isHit()){
            this.receiveCard(deck.getCard());
        }
    }

    public int getBettingMoney() {
        return bettingMoney.getValue();
    }
}
