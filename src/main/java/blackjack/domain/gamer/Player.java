package blackjack.domain.gamer;

import blackjack.domain.card.Cards;
import blackjack.domain.GameTable;
import blackjack.domain.card.Card;

import java.util.List;
import java.util.regex.Pattern;

public class Player extends Participant {

    public Player(Name name, GameTable gameTable) {
        this(name, gameTable.initCards());
    }

    public Player(Name name, List<Card> cards) {
        super(name, cards);
    }



    @Override
    public boolean isAvailableToTake() {
        return super.sumCards() <= Cards.BLACK_JACK;
    }
}
