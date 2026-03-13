package model;

import static model.Dealer.DEALER_NAME;

import constant.ErrorMessage;
import java.math.BigDecimal;
import java.util.Objects;
import model.dto.PlayerResult;
import model.dto.PlayerWinning;

public class Player extends Participant {
    private Integer betAmount = 0;

    public Player(PlayerName name) {
        super(validate(name));
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

    public PlayerWinning getBetResult(Dealer dealer) {
        Integer profit = calculateBetAmount(dealer);
        return new PlayerWinning(getName(), profit);
    }

    private static PlayerName validate(PlayerName name) {
        if(name.getName().equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ErrorMessage.NO_PLAYER_NAME_DEALER.getMessage());
        }
        return name;
    }

    private Integer calculateBetAmount(Dealer dealer) {
        return calculateBustBetAmount(dealer).intValue();
    }

    private BigDecimal calculateBustBetAmount(Dealer dealer) {
        if(super.isBust()) {
            return BigDecimal.valueOf(-betAmount);
        }

        return calculateBlackJackBetAmount(dealer);
    }

    private BigDecimal calculateBlackJackBetAmount(Dealer dealer) {

        if(super.isBlackJack() && dealer.isBlackJack()) {
            return BigDecimal.ZERO;
        }

        if(super.isBlackJack()) {
            return BigDecimal.valueOf(betAmount).multiply(BigDecimal.valueOf(1.5));
        }

        return calculateRegularBetAmount(dealer);
    }

    private BigDecimal calculateRegularBetAmount(Dealer dealer) {
        if(super.isMoreThanScore(dealer) || dealer.isBust()) {
            return BigDecimal.valueOf(betAmount);
        }

        if(super.isLessThanScore(dealer)) {
            return BigDecimal.valueOf(betAmount);
        }
        return BigDecimal.ZERO;
    }
}
