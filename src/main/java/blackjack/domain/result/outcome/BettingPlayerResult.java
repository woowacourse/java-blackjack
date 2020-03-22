package blackjack.domain.result.outcome;

import blackjack.domain.participant.BettingPlayer;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.attribute.Money;
import blackjack.domain.participant.attribute.Name;
import blackjack.domain.result.ResultRule;
import blackjack.domain.result.ResultType;

import java.util.Objects;

import static blackjack.domain.card.Card.NULL_ERR_MSG;

// TODO: 2020-03-23 Name, Money, ResultTye 입력받는 생성자 삭제 고려
public class BettingPlayerResult implements Reportable<Double> {
    private Name name;
    private Money bettingMoney;
    private ResultType resultType;

    public BettingPlayerResult(BettingPlayer player, Dealer dealer) {
        Objects.requireNonNull(player, NULL_ERR_MSG);
        Objects.requireNonNull(dealer, NULL_ERR_MSG);
        this.name = player.getName();
        this.bettingMoney = player.getMoney();
        this.resultType = ResultRule.findResult(player.getCards(), dealer.getCards());
    }

    public BettingPlayerResult(Name name, Money bettingMoney, ResultType resultType) {
        Objects.requireNonNull(name, NULL_ERR_MSG);
        Objects.requireNonNull(bettingMoney, NULL_ERR_MSG);
        Objects.requireNonNull(resultType, NULL_ERR_MSG);
        this.name = name;
        this.bettingMoney = bettingMoney;
        this.resultType = resultType;
    }

    public Name getName() {
        return name;
    }

    @Override
    public Double showPlayerResult() {
        return bettingMoney.computeProfit(resultType);
    }
}
