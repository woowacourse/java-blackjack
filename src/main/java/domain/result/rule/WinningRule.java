package domain.result.rule;

import domain.gamer.Gamer;

public class WinningRule implements MatchRule {
    @Override
    public boolean isMatch(Gamer dealer, Gamer player) {
        return (player.isNotBust() && (dealer.isBust() || player.calculateScore().compareTo(dealer.calculateScore()) > 0))
                && player.isNotBlackJack();
    }
}
