package model.participant;

import java.util.List;
import model.betting.Bet;
import model.card.Card;
import model.card.Cards;

public class Player extends Participant {

    private static final int HIT_CONDITION = 22;

    private Bet bet;

    public Player(String name) {
        this(name, List.of());
    }

    public Player(String name, List<Card> cards) {
        super(Name.createPlayerName(name), new Cards(cards));
    }

    @Override
    public boolean isPossibleHit() {
        return score() < HIT_CONDITION;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public Bet getBet() {
        return bet;
    }
}
