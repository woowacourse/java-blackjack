package blackjack.domain.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.vo.BettingMoney;
import blackjack.domain.entry.vo.Name;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;
import java.util.List;

public class Player extends Participant {

    private final BettingMoney bettingMoney;
    private State state;

    public Player(Name name, BettingMoney bettingMoney) {
        super(name);
        validateName(name);
        this.bettingMoney = bettingMoney;
    }

    public void ready(Card first, Card second) {
        this.state = Ready.start(first, second);
    }

    public void draw(Card card) {
        this.state = state.draw(card);
    }

    public void stay() {
        this.state = state.stay();
    }

    @Override
    public int countScore() {
        return state.score();
    }

    public double profit(Dealer dealer) {
        return state.profit(bettingMoney, dealer);
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean equalsName(Name name) {
        return getName().equals(name);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public List<Card> getCards() {
        return state.getHoldCards().getCards();
    }

    private void validateName(Name name) {
        if (name.getValue().equals(Dealer.NAME)) {
            throw new IllegalArgumentException("플레이어의 이름은 딜러의 이름이 될 수 없습니다.");
        }
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
