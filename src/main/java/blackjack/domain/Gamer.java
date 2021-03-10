package blackjack.domain;

import blackjack.domain.state.Hit;
import blackjack.domain.state.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Gamer {

    public static final int NUM_INIT_CARD = 2;
    private static final String SPACE = " ";
    private static final String ERROR_NAME_SPACE = "이름에 공백이 포함됩니다.";

    private static final String COMMA = ", ";
    public static final String ERROR_NAME_LENGTH = "이름이 공백일 수는 없습니다.";
    private final String name;
    protected BettingMoney bettingMoney;
    protected int earnedMoney;
    protected State state = new Hit(new Cards());

    protected Gamer(String name) {
        validateSpace(name);
        validateZeroLength(name);
        earnedMoney = 0;
        this.name = name;
    }

    private static void validateZeroLength(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException(ERROR_NAME_LENGTH);
        }
    }

    private static void validateSpace(String name) {
        if (name.contains(SPACE)) {
            throw new IllegalArgumentException(ERROR_NAME_SPACE);
        }
    }

    public abstract boolean canReceiveCard();

    protected void receiveCard(Card card) {
        state = state.draw(card);
    }

    public boolean isBust() {
        return state.isBust();
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public int getPoint() {
        return state.cards().getPoint();
    }

    public abstract Boolean continueDraw(Deck deck);

    public String getName() {
        return name;
    }

    public String getAllCards() {
        return state.cards().getCards().
            stream()
            .map(Card::getPatternAndNumber)
            .collect(Collectors.joining(COMMA));
    }

    public String getOpenCards() {
        List<Card> dealerCards = new ArrayList<>();
        for (int i = 0; i < state.cards().size() - 1; i++) {
            dealerCards.add(state.cards().get(i));
        }
        return dealerCards.stream()
            .map(Card::getPatternAndNumber)
            .collect(Collectors.joining(COMMA));
    }

    public void calculateProfit(Dealer dealer) {
        if ((dealer.isBlackjack() && this.isBlackjack())) {
            earnedMoney = 0;
        } else if (dealer.getPoint() == this.getPoint()) {
            earnedMoney = 0;
        } else if (dealer.getPoint() < this.getPoint()) {
            earnedMoney = state.profit(bettingMoney);
        } else if (dealer.getPoint() > this.getPoint()) {
            earnedMoney = -bettingMoney.getBettingMoney();
        }
        dealer.giveMoney(-this.earnedMoney);
    }

    public int getProfit() {
        return this.earnedMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Gamer)) {
            return false;
        }
        Gamer gamer = (Gamer) o;
        return Objects.equals(name, gamer.name) &&
            Objects.equals(state, gamer.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, state);
    }

}
