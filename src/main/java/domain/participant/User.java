package domain.participant;

import java.util.ArrayList;
import java.util.List;

import domain.Result;
import domain.card.Card;
import domain.card.Deck;
import domain.hand.Hand;
import domain.hand.Score;

public class User implements Player {

    private final String name;
    private final PlayerAction playerAction;

    public User(String name) {
        this(name, new ArrayList<>());
    }

    public User(String name, List<Card> cards) {
        this.name = name;
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
        return playerAction.getScore().isLessThan(Score.BLACKJACK);
    }

    @Override
    public Hand getHand() {
        return playerAction.getHand();
    }

    @Override
    public String getName() {
        return name;
    }
}