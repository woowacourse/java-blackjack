package blackjack.participant;

import blackjack.card.Cards;
import blackjack.result.Betting;
import blackjack.result.GameResult;
import blackjack.result.Money;

import java.util.function.Consumer;
import java.util.function.Function;

public class Player extends GameParticipant {

    private final Function<Player, Boolean> hitDecision;
    private final Consumer<GameParticipant> handDisplay;
    private final Betting betting;

    private Player(Nickname nickname,
                   Cards hand,
                   Betting betting,
                   Function<Player, Boolean> hitDecision,
                   Consumer<GameParticipant> handDisplay) {
        super(nickname, hand);
        this.betting = betting;
        this.hitDecision = hitDecision;
        this.handDisplay = handDisplay;
    }

    public static Player of(Nickname nickname,
                            Betting betting,
                            Function<Player, Boolean> hitDecision,
                            Consumer<GameParticipant> handDisplay) {
        return new Player(nickname, Cards.empty(), betting, hitDecision, handDisplay);
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
        if (hand.isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        return GameResult.of(this.calculateSumOfCards(), dealer.calculateSumOfCards());
    }

    public Money calcProfit(GameResult result) {
        return betting.applyProfit(result);
    }
}
