package blackjack.domain.participants;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.state.Start;
import blackjack.domain.state.State;
import blackjack.domain.winning.WinningResult;
import java.util.Objects;

public class Player {
    private final String name;
    private final Cards cards;
    private final BettingMoney bettingMoney;
    private State state;

    public Player(String name, Cards cards, BettingMoney bettingMoney) {
        this.name = name.trim();
        this.cards = cards;
        this.bettingMoney = bettingMoney;
    }

    public void prepareCards(Deck deck) {
        cards.take(deck.draw(), deck.draw());
    }

    public void prepareCards2(Deck deck) {
        state = Start.of(deck.draw(), deck.draw());
    }

    public void drawCard(Deck deck) {
        cards.take(deck.draw());
    }

    public void drawCard2(Deck deck) {
        state.draw(deck.draw());
    }

    public Score calculateMaxScore() {
        return cards.calculateMaxScore();
    }

    public int calculateProfit(Cards competitiveCards) {
        WinningResult winningResult = WinningResult.decide(cards, competitiveCards);
        return bettingMoney.calculateProfit(winningResult);
    }

    public double calculateProfit2(State competitiveState) {
        WinningResult winningResult = state.decide(competitiveState);
        if (winningResult == WinningResult.WIN) {
            return state.profit(bettingMoney.amount());
        }
        if (winningResult == WinningResult.DRAW) {
            return 0;
        }
        return -bettingMoney.amount();
    }

    public Cards getCards() {
        return cards;
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
        return getName().equals(player.getName()) && Objects.equals(getCards(), player.getCards())
                && Objects.equals(bettingMoney, player.bettingMoney);
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + Objects.hashCode(getCards());
        result = 31 * result + Objects.hashCode(bettingMoney);
        return result;
    }
}
