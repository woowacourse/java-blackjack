package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class User extends AbstractPlayer {
    private static final String YES = "yY";
    private static final String NO = "nN";

    private BetAmount betAmount;

    public User(String name) {
        this(name, Cards.getInstance().draw(), Cards.getInstance().draw());
    }

    public User(String name, Card firstCard, Card secondCard) {
        super(name, firstCard, secondCard);
    }

    public boolean isDrawContinue(String input) {
        drawInputValidate(input);
        if (YES.contains(input)) {
            return true;
        }
        stay();
        return !isFinished();
    }

    private void drawInputValidate(String value) {
        if (!(YES.contains(value) || NO.contains(value))) {
            throw new IllegalArgumentException("입력은 y(Y) 또는 n(N)만 가능합니다.");
        }
    }

    public void setBetAmount(String betAmount) {
        this.betAmount = new BetAmount(betAmount);
    }

    public int profit(Dealer dealer) {
        return (int) state.profit(dealer.state, betAmount);
    }
}