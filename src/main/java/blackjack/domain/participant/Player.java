package blackjack.domain.participant;

import blackjack.domain.result.Result;

public class Player extends Participant {
    private PlayerMoney playerMoney;

    public Player(final String name) {
        this(name, new PlayerMoney(0));
    }

    public Player(final String name, final double playerMoney) {
        this(name, new PlayerMoney(playerMoney));
    }

    public Player(final String name, final PlayerMoney playerMoney) {
        super(name);
        this.playerMoney = playerMoney;
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

    public Double generateProfit(Dealer dealer) {
        final Result result = generateResult(dealer);
        final double profit = playerMoney.calculateProfit(result.getProfitRate());
        dealer.calculateProfit(profit);
        return profit;
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
