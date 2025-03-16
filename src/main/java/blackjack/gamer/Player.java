package blackjack.gamer;

import blackjack.card.Hand;
import blackjack.result.BlackjackMatchResult;

public class Player extends Gamer {
    private final Nickname nickname;
    private final Betting betting;

    public Player(Hand hand, Nickname nickname, Betting betting) {
        super(hand);
        this.nickname = nickname;
        this.betting = betting;
    }

    public double getProfit(BlackjackMatchResult result) {
        return (result.getRatio() * state.getProfit(betting.getAmount()));
    }

    @Override
    public String getNickname() {
        return nickname.getName();
    }
}
