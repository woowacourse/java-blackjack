package blackjack.gamer;

import blackjack.domain.card.Cards;

import java.util.function.Function;

public class Player extends GameParticipant {

    private final Function<Player, Boolean> hitDecision;

    public Player(Nickname nickname, Cards hand, Function<Player, Boolean> hitDecision) {
        super(nickname, hand);
        this.hitDecision = hitDecision;
    }

    public static Player of(Nickname nickname, Function<Player, Boolean> hitDecision) {
        return new Player(nickname, Cards.empty(), hitDecision);
    }

    @Override
    public Cards showHand() {
        return hand;
    }

    @Override
    public boolean shouldHit() {
        return !this.isBust() && hitDecision.apply(this);
    }
}
