package domain.result.rule;

import domain.gamer.Gamer;

public class DrawRule implements MatchRule {
    @Override
    public boolean isMatch(Gamer dealer, Gamer player) {
        return (player.isNotBust() && player.isNotBlackJack() && dealer.isNotBlackJack() && player.calculateScore() == dealer.calculateScore())
                || (dealer.isBlackJack() && player.isBlackJack());
    }
}
