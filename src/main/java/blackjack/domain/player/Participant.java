package blackjack.domain.player;

import blackjack.domain.Outcome;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.bet.Profit;
import blackjack.domain.card.Deck;
import blackjack.domain.state.Ready;

public class Participant extends AbstractPlayer {

    private final BetMoney money;

    public Participant(Name name, Deck deck, BetMoney money) {
        this.name = name;
        this.state = Ready.dealToParticipant(deck.pick(), deck.pick());
        this.money = money;
    }

    public BetMoney getBetMoney() {
        return money;
    }

    public Profit getProfit(Outcome outcome) {
        return state.profit(outcome, money);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name=" + name +
                ", playerCards=" + state.getCards() +
                '}';
    }
}
