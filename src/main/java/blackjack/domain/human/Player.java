package blackjack.domain.human;

import static blackjack.util.Constants.BLACKJACK_NUMBER;

import blackjack.domain.Result;
import blackjack.domain.card.Cards;
import java.util.Objects;

public class Player extends Human {
    private Result result;

    private Player(String name) {
        super(Cards.of(), name);
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public void calculateResult(final int dealerPoint) {
        int point = getPoint();
        if (dealerPoint > BLACKJACK_NUMBER) {
            this.result = getResultIfDealerBurst(point);
            return;
        }
        this.result = getResultIfDealerNotBurst(dealerPoint, point);
    }

    private Result getResultIfDealerBurst(final int point) {
        if (point <= BLACKJACK_NUMBER) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private Result getResultIfDealerNotBurst(final int dealerPoint, final int point) {
        if (point > BLACKJACK_NUMBER || dealerPoint > point) {
            return Result.LOSE;
        }
        if (dealerPoint == point) {
            return Result.DRAW;
        }
        return Result.WIN;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public boolean isAbleToHit() {
        return cards.getPoint() < BLACKJACK_NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return result == player.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cards=" + cards +
                ", result=" + result +
                '}';
    }
}
