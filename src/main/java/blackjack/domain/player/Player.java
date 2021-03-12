package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;

public abstract class Player {

    protected final String name;
    protected State state;
    protected double money;

    public Player(String name, State state) {
        this.name = name;
        this.state = state;
    }

    abstract void addCard(Card card);

    abstract boolean canDraw();

    public abstract boolean isDealer();

    public final int calculateScore() {
        return state.calculateScore();
    }

    public void betMoney(double money) {
        if (money <= 0) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 베팅 금액은 0원 이상이어야합니다.");
        }
        this.money = money;
    }

    public final boolean isBust() {
        return state.isBust();
    }

    public final boolean isBlackjack() {
        return state.isBlackjack();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return state.cards();
    }

    public State getState() {
        return state;
    }
}
