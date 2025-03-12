package blackjack.domain.gamer;

import blackjack.domain.card.Cards;
import blackjack.domain.result.GameResult;

import java.util.function.Consumer;
import java.util.function.Function;

public class Player extends GameParticipant {

    private final Function<Player, Boolean> hitDecision;
    private final Consumer<GameParticipant> handDisplay;

    private Player(Nickname nickname, Cards hand,
                   Function<Player, Boolean> hitDecision,
                   Consumer<GameParticipant> handDisplay) {
        super(nickname, hand);
        this.hitDecision = hitDecision;
        this.handDisplay = handDisplay;
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

    @Override
    public void showHand() {
        this.handDisplay.accept(this);
    }

    public GameResult judgeResult(Dealer dealer) {
        return GameResult.of(this.calculateSumOfCards(), dealer.calculateSumOfCards());
    }
}
