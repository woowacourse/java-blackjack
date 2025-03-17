package blackjack.gamer;

import blackjack.card.Hand;
import blackjack.result.BlackjackMatchResult;
import java.math.BigDecimal;

public class Player extends Gamer {
    private final Nickname nickname;
    private final Betting betting;

    public Player(Hand hand, Nickname nickname, Betting betting) {
        super(hand);
        this.nickname = nickname;
        this.betting = betting;
    }

    public BigDecimal getProfit(BlackjackMatchResult result) {
        return state.getProfit(betting.getAmount()).multiply(BigDecimal.valueOf(result.getRatio()));
    }

    @Override
    public String getNickname() {
        return nickname.getName();
    }
}
