package blackjack.domain.participants;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.winning.WinningResult;
import java.util.Objects;

public class Player {
    private final String name;
    private final Cards cards;
    private final int battingMoney;

    public Player(String name, Cards cards, int battingMoney) {
        this.name = name.trim();
        this.cards = cards;
        this.battingMoney = battingMoney;
    }

    public void prepareCards(Deck deck) {
        cards.take(deck.draw(), deck.draw());
    }

    public int calculateRevenue(Dealer dealer) {
        if (cards.isBust()) {
            dealer.plusAmount(battingMoney);
            return -battingMoney;
        }
        WinningResult winningResult = WinningResult.decide(cards, dealer.getCards());
        if (winningResult == WinningResult.WIN) {
            if (cards.isBlackjack()) {
                int revenue = (int) (battingMoney * 1.5 - battingMoney);
                dealer.minusAmount(revenue);
                return revenue;
            }
            dealer.minusAmount(battingMoney);
            return battingMoney;
        }
        if (winningResult == WinningResult.LOSE) {
            dealer.plusAmount(battingMoney);
            return -battingMoney;
        }
        return 0;
    }

    public void drawCard(Deck deck) {
        cards.take(deck.draw());
    }

    public int calculateMaxScore() {
        return cards.calculateMaxScore();
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
        return battingMoney == player.battingMoney && getName().equals(player.getName()) && Objects.equals(
                getCards(), player.getCards());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + Objects.hashCode(getCards());
        result = 31 * result + battingMoney;
        return result;
    }
}
