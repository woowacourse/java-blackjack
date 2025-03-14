package blackjack.domain.participants;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.winning.WinningResult;
import java.util.Objects;

public class Player {
    private final String name;
    private final Cards cards;
    private final BattingMoney battingMoney;

    public Player(String name, Cards cards, BattingMoney battingMoney) {
        this.name = name.trim();
        this.cards = cards;
        this.battingMoney = battingMoney;
    }

    public void prepareCards(Deck deck) {
        cards.take(deck.draw(), deck.draw());
    }

    public void drawCard(Deck deck) {
        cards.take(deck.draw());
    }

    public Score calculateMaxScore() {
        return cards.calculateMaxScore();
    }

    public int calculateProfit(Cards competitiveCards) {
        WinningResult winningResult = WinningResult.decide(cards, competitiveCards);
        return battingMoney.calculateProfit(winningResult);
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
                && Objects.equals(battingMoney, player.battingMoney);
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + Objects.hashCode(getCards());
        result = 31 * result + Objects.hashCode(battingMoney);
        return result;
    }
}
