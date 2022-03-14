package blackjack.domain.participant;

import static blackjack.domain.card.Hand.BLACKJACK_SYMBOL_SCORE;

import blackjack.domain.card.Hand;
import blackjack.domain.result.Result;
import java.util.Objects;

public class Player extends Participant {

    public Player(String name) {
        this(new Name(name));
    }

    public Player(Name name) {
        this(name, new Hand());
    }

    public Player(Name name, Hand cardHand) {
        super(name, cardHand);
    }

    @Override
    public boolean shouldReceive() {
        return !cardHand.isBust() && !cardHand.isBlackjack() && cardHand.getScore() != BLACKJACK_SYMBOL_SCORE;
    }

    public Result compareMatchResult(int dealerCardScore) {
        if (cardHand.getScore() < dealerCardScore || cardHand.isBust()) {
            return Result.LOSE;
        }
        if (dealerCardScore == cardHand.getScore()) {
            return Result.DRAW;
        }
        if (dealerCardScore < cardHand.getScore()) {
            return Result.WIN;
        }
        throw new IllegalArgumentException("[ERROR] 입력 값이 올바르지 않습니다.");
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
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
