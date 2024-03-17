package participant.player;

import card.Card;
import java.util.List;
import participant.Participant;

public class Player extends Participant {

    private final BetMoney betMoney;

    public Player(List<Card> cardDeck, Name name, BetMoney betMoney) {
        super(cardDeck, name);
        this.betMoney = betMoney;
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }
}
