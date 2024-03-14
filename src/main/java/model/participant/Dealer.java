package model.participant;

import java.util.List;
import model.card.Card;
import model.card.Cards;

public class Dealer extends Participant {

    private static final int HIT_CONDITION = 17;

    public Dealer() {
        this(new Cards(List.of()));
    }

    public Dealer(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isPossibleHit() {
        int totalNumbers = cards.calculateTotalScore();
        return totalNumbers < HIT_CONDITION;
    }

    public Card getFirstCard() {
        return cards.getCards().get(0);
    }
}
