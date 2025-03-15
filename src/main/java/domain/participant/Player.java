package domain.participant;

import domain.card.Card;
import domain.result.ResultStatus;

import java.util.List;
import java.util.Objects;

public class Player extends Participant {

    private final BetAmount betAmount;

    public Player(String name, List<Card> cards, int amount) {
        super(name, cards);
        this.betAmount = new BetAmount(amount);
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public Integer calculateIncome(Dealer dealer) {
        ResultStatus resultStatus = ResultStatus.judgeGameResult(this, dealer);
        return betAmount.calculateIncome(resultStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name)
            && Objects.equals(cards, player.cards)
            && Objects.equals(betAmount, player.betAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards, betAmount);
    }
}
