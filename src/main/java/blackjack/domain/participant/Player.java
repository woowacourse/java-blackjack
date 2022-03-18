package blackjack.domain.participant;

import blackjack.domain.BettingMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;
import java.util.Collections;
import java.util.List;

public class Player {

    private final Name name;
    private final BettingMoney bettingMoney;
    private State state;

    public Player(String name, int money) {
        this.name = new Name(name);
        this.bettingMoney = new BettingMoney(money);
        this.state = new Ready();
    }

    public boolean isReady() {
        return state.isRunning();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public void hit(Card card) {
        if (!isFinished()) {
            state = state.draw(card);
        }
    }

    public void stay() {
        state = state.stay();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        Cards cards = state.cards();
        return Collections.unmodifiableList(cards.getValue());
    }
}
