package blackjack.gamer;

import blackjack.domain.card.Cards;

import java.util.function.Consumer;
import java.util.function.Function;

public class Player extends GameParticipant {

    private final Function<Player, Boolean> hitDecision;

    private Player(Nickname nickname, Cards hand,
                   Function<Player, Boolean> hitDecision,
                   Consumer<GameParticipant> handDisplay) {
        super(nickname, hand, handDisplay);
        this.hitDecision = hitDecision;
    }

    public static Player of(Nickname nickname,
                            Function<Player, Boolean> hitDecision,
                            Consumer<GameParticipant> handDisplay) {
        return new Player(nickname, Cards.empty(), hitDecision, handDisplay);
    }

    @Override
    public boolean shouldHit() {
        return !this.isBust() && hitDecision.apply(this);
    }
}
