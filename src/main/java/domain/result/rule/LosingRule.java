package domain.result.rule;

import domain.gamer.Gamer;

public class LosingRule implements MatchRule {
    @Override
    public boolean isMatch(Gamer dealer, Gamer player) {
        return player.isBust()
                || player.calculateScore().compareTo(dealer.calculateScore()) < 0
                || (dealer.isBlackJack() && player.isNotBlackJack());
    }
}
