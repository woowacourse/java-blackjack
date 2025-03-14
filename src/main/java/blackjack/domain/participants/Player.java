package blackjack.domain.participants;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.state.State;
import blackjack.domain.winning.WinningResult;
import java.util.Objects;

public class Player {
    private final String name;
    private State state;
    private final BettingMoney bettingMoney;

    public Player(String name, State state, BettingMoney bettingMoney) {
        this.name = name.trim();
        this.state = state;
        this.bettingMoney = bettingMoney;
    }

    public void drawCard(Deck deck) {
        state = state.draw(deck.draw());
    }

    public Score calculateMaxScore() {
        return state.calculateTotalScore();
    }

    public int calculateProfit(State competitiveState) {
        WinningResult winningResult = state.decide(competitiveState);
        if (winningResult == WinningResult.WIN) {
            return (int) state.profit(bettingMoney.amount());
        }
        if (winningResult == WinningResult.DRAW) {
            return 0;
        }
        return -bettingMoney.amount();
    }

    public void stay() {
        state = state.stay();
    }

    public Cards getCards() {
        return state.cards();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Player player = (Player) object;
        return getName().equals(player.getName()) && Objects.equals(state, player.state)
                && Objects.equals(bettingMoney, player.bettingMoney);
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + Objects.hashCode(state);
        result = 31 * result + Objects.hashCode(bettingMoney);
        return result;
    }
}
