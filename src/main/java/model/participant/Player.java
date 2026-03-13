package model.participant;

import java.util.Objects;
import model.result.ProfitCalculator;
import dto.status.DealerStatus;
import dto.result.PlayerResult;
import dto.status.PlayerStatus;
import dto.result.PlayerWinning;

public class Player extends Participant {
    private Integer betAmount = 0;

    public Player(PlayerName name) {
        super(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player other = (Player) o;
        return this.getResult().name().equals(other.getResult().name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getResult().name());
    }

    @Override
    public PlayerResult getResult() {
        PlayerResult result = super.getResult();
        return new PlayerResult(result.name(), result.deck(), result.score(), betAmount);
    }

    public void setBetAmount(BetPrice betPrice) {
        this.betAmount = betPrice.value();
    }

    public PlayerWinning getBetResult(DealerStatus dealerStatus) {
        PlayerStatus playerStatus = new PlayerStatus(super.getName(), super.getScore(), betAmount, super.isBust(), super.isBlackJack());
        Integer profit = ProfitCalculator.calculateBetAmount(dealerStatus, playerStatus);
        return new PlayerWinning(getName(), profit);
    }
}
