package model.participants;

import static model.participants.ParticipantType.TYPE_PLAYER;

import model.bettings.Wager;
import model.cards.Hand;

public class Player extends Participant {
    private static final int PLAYER_HIT_THRESHOLD = 21;

    private final Name name;

    public Player(Name name, Wager wager, Hand hand) {
        this.name = name;
        this.wager = wager;
        this.hand = hand;
    }

    public void updateWager(double multiplier) {
        wager.updateWager(ParticipantType.isDealer(TYPE_PLAYER), multiplier);
    }

    public boolean isBust() {
        return hand.getScore() > PLAYER_HIT_THRESHOLD;
    }

    public String getName() {
        return name.playerName();
    }

    @Override
    public boolean canHit() {
        return hand.getScore() < PLAYER_HIT_THRESHOLD;
    }

    @Override
    public void calculateHandScore() {
        hand.calculateScore(ParticipantType.isDealer(TYPE_PLAYER));
    }
}
