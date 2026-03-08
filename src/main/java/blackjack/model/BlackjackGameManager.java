package blackjack.model;

import java.util.List;

public class BlackjackGameManager {
    private final ScoreCalculator scoreCalculator;
    private final BlackjackRule rule;

    public BlackjackGameManager(ScoreCalculator scoreCalculator, BlackjackRule rule) {
        this.scoreCalculator = scoreCalculator;
        this.rule = rule;
    }

    public boolean canHit(Participant player) {
        Score score = calculate(player.getCards());
        return rule.canHitPlayer(score);
    }

    public boolean shouldHit(Dealer dealer) {
        Score score = calculate(dealer.getCards());
        return rule.shouldHitDealer(score);
    }

    public Score calculate(List<Card> cards) {
        return scoreCalculator.calculate(cards);
    }

    public PlayerBlackjackResult judge(Participant player, Dealer dealer) {
        return rule.judge(calculate(player.getCards()), calculate(dealer.getCards()));
    }
}
