package blackjack.domain.result.outcome;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.attribute.Name;
import blackjack.domain.result.ResultRule;
import blackjack.domain.result.ResultType;

import java.util.Objects;

import static blackjack.domain.card.Card.NULL_ERR_MSG;

// TODO: 2020-03-23 Name, ResultTye 입력받는 생성자 삭제 고려

public class PlayerResult implements Reportable<ResultType> {
    private Name name;
    private ResultType resultType;

    public PlayerResult(Player player, Dealer dealer) {
        Objects.requireNonNull(player, NULL_ERR_MSG);
        Objects.requireNonNull(dealer, NULL_ERR_MSG);
        this.name = player.getName();
        this.resultType = ResultRule.findResult(player.getCards(), dealer.getCards());
    }

    public PlayerResult(Name name, ResultType resultType) {
        Objects.requireNonNull(name, NULL_ERR_MSG);
        Objects.requireNonNull(resultType, NULL_ERR_MSG);
        this.name = name;
        this.resultType = resultType;
    }

    @Override
    public ResultType showPlayerResult() {
        return resultType;
    }

    public boolean hasSameResult(ResultType type) {
        return this.resultType == type;
    }

    public Name getName() {
        return name;
    }

    public ResultType getResultType() {
        return resultType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerResult that = (PlayerResult) o;
        return Objects.equals(name, that.name) &&
                resultType == that.resultType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, resultType);
    }


}
