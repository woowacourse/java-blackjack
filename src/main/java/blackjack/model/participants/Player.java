package blackjack.model.participants;

import blackjack.model.cards.Cards;
import blackjack.model.state.BlackJack;
import blackjack.model.state.Hit;
import blackjack.model.state.Stand;
import blackjack.vo.Money;
import blackjack.vo.Name;

public class Player extends Participant {
    private final Name name;
    private Money betAmount = new Money();

    public Player(String name) {
        this.name = new Name(name);
    }

    @Override
    public boolean canHit() {
        return state.getClass().equals(Hit.class) || state.getClass().equals(BlackJack.class);
    }

    public void betMoney(Money betMoney) {
        betAmount = betMoney;
    }

    public Money evaluateProfit(Cards otherCards) {
        if (state.getClass().equals(BlackJack.class) && otherCards.isBlackJack()) {
            return new Money();
        }
        if (state.getClass().equals(Stand.class) && !otherCards.isBust()) {
            return compareScore(otherCards, betAmount);
        }
        return state.calculateProfit(betAmount);
    }

    private Money compareScore(Cards otherCards, Money betAmount) {
        Stand stand = (Stand) state;
        int score = stand.getScore();
        if (score > otherCards.getScore()) {
            return state.calculateProfit(betAmount);
        }
        if (score < otherCards.getScore()) {
            return state.calculateProfit(new Money(-betAmount.value()));
        }
        return new Money();
    }

    public String getName() {
        return name.value();
    }
}
