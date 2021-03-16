package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.state.State;

import java.util.List;

public class Player extends AbstractParticipant {
    private int money;

    public Player(String name, State state) {
        super(name, state);
        changeState();
    }

    @Override
    public boolean isReceivable() {
        return !isEnd();
    }

    @Override
    public boolean handOutCard(Card card) {
        if (!isReceivable()) {
            return false;
        }

        receiveCard(card);
        changeState();
        return true;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public List<Card> showInitCards() {
        return showCards();
    }

    public void betting(int money) {
        this.money = money;
    }

    public int calculateWinPrize(State enemyState) {
        return (int) (money * getState().calculateEarningRate(enemyState));
    }
}
