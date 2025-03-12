package domain.participant;

import domain.card.BlackJackCards;
import domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private static final int PICK_DECISION_VALUE = 21;

    public Player(String name) {
        super(name, new BlackJackCards());
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public List<Card> getShownCard() {
        return getCards();
    }

    @Override
    public boolean canPick() {
        return getTotalValue() <= PICK_DECISION_VALUE;
    }
}
