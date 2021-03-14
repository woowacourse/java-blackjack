package blackjack.domain.participant;

import blackjack.domain.result.Result;

public class Player extends Participant {
    public Player(final String name) {
        this(name, 0);
    }

    public Player(final String name, final int playerMoney) {
        super(name, playerMoney);
    }

    @Override
    public Result generateResult(Participant participant) {
        if (this.isBlackjack() && !participant.isBlackjack()) {
            return Result.BLACKJACK_WIN;
        }
        if (this.isBust()) {
            return Result.LOSE;
        }
        if (participant.isBust()) {
            return Result.WIN;
        }
        return generateResultByScore(participant);
    }

    public Integer generateProfit(Dealer dealer) {
        final Result result = generateResult(dealer);
        return money.calculateProfit(result.getProfitRate());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Player otherPlayer = (Player) obj;
        return this.name.equals(otherPlayer.name);
    }
}
