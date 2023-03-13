package blackjack.domain.player;

import blackjack.domain.result.ResultMatcher;

public class Player extends User {

    private static final int SCORE_LIMIT = 21;
    private BetAmount betAmount;
    private ResultMatcher resultMatcher;

    public Player(Name name) {
        super(name);
        this.name = name;
    }

    public String getPlayerName() {
        return name.getName();
    }

    public void inputBetAmount(int betAmount) {
        this.betAmount = new BetAmount(betAmount);
    }

    public void checkBlackJack(int score) {
        if (score == 21) {
            this.resultMatcher = ResultMatcher.BLACKJACK;
        }
    }

    @Override
    public boolean isUnderLimit() {
        return hand.getTotalScore() < SCORE_LIMIT;
    }
}
