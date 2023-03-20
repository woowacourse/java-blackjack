package domain.participant;

import java.util.ArrayList;
import java.util.List;

import domain.Result;
import domain.card.Card;
import domain.card.Deck;
import domain.hand.Hand;
import domain.hand.Score;

public class Dealer implements Player {

    private static final String DEFAULT_NAME = "딜러";
    private static final Score UNHITTABLE_MIN = new Score(17);

    private final PlayerAction playerAction;

    public Dealer() {
        this(new ArrayList<>());
    }

    public Dealer(List<Card> cards) {
        this.playerAction = new PlayerAction(cards);
    }

    @Override
    public void drawFrom(Deck deck) {
        playerAction.drawFrom(deck);
    }

    @Override
    public Result competeWith(Player other) {
        return playerAction.competeWith(other);
    }

    @Override
    public boolean canHit() {
        return playerAction.getScore().isLessThan(UNHITTABLE_MIN);
    }

    @Override
    public Hand getHand() {
        return playerAction.getHand();
    }

    @Override
    public String getName() {
        return DEFAULT_NAME;
    }
}


