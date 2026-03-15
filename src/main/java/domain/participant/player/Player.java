package domain.participant.player;

import domain.card.Card;
import domain.participant.Hand;
import domain.participant.Participant;

import java.math.BigDecimal;

public class Player extends Participant {
    private static final int PLAYER_MAX_HITTABLE_SCORE = 21;
    private final PlayerInfo playerInfo;

    public Player(PlayerInfo playerInfo) {
        super(new Hand());
        this.playerInfo = playerInfo;
    }

    @Override
    public boolean canHit() {
        return getTotalCardScore() < PLAYER_MAX_HITTABLE_SCORE;
    }

    @Override
    public void keepCard(Card card) {
        if (canHit()) {
            this.getHand().addCard(card);
        }
    }

    @Override
    public String getName() {
        return playerInfo.getName();
    }

    public BigDecimal getBettingMoney() {
        return playerInfo.getBettingMoney();
    }
}
