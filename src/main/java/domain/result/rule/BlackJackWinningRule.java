package domain.result.rule;

import domain.gamer.Gamer;

public class BlackJackWinningRule implements MatchRule {

    @Override
    public boolean isMatch(Gamer dealer, Gamer player) {
        return (dealer.isNotBlackJack() && player.isBlackJack());
    }
}
