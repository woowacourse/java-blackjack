package domain.player;

import domain.CardPossessor;
import domain.CardProvider;
import domain.result.WinLose;

import java.util.Objects;

public abstract class BlackJackPlayer {
    protected final String name;
    protected final CardPossessor cardPossessor;

    public BlackJackPlayer(String name, CardPossessor cards) {
        this.name = name;
        this.cardPossessor = cards;
    }

    public WinLose determineWinLose(BlackJackPlayer counterParts) {
        if (this.calculateScore() > counterParts.calculateScore()) {
            return WinLose.WIN;
        }

        return WinLose.LOSE;
    }

    public void drawCard(CardProvider cardProvider) {
        cardPossessor.drawCard(cardProvider);
    }

    public int calculateScore() {
        return cardPossessor.calculateScore();
    }

    public abstract boolean canDrawMore();

    public CardPossessor getCardsOnHand() {
        return cardPossessor;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackJackPlayer that = (BlackJackPlayer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
