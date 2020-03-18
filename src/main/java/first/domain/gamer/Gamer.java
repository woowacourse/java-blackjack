package first.domain.gamer;

import first.domain.card.possessable.CardPossessable;
import first.domain.card.providable.CardProvidable;
import first.domain.result.WinLose;
import first.domain.score.Calculatable;

import java.util.Objects;

import static first.domain.score.ScoreManagable.BLACK_JACK_SCORE;

public abstract class Gamer implements BlackJackGameable {
    private final String name;
    private final CardPossessable cardPossessable;

    Gamer(String name, CardPossessable cards) {
        this.name = name;
        this.cardPossessable = cards;
    }

    @Override
    public WinLose determineWinLose(BlackJackGameable counterParts) {
        if (this.calculateScore().isLargerThan(BLACK_JACK_SCORE)) {
            return WinLose.LOSE;
        }

        if (counterParts.calculateScore().isLargerThan(BLACK_JACK_SCORE)) {
            return WinLose.WIN;
        }

        if (this.calculateScore().isLargerThan(counterParts.calculateScore())) {
            return WinLose.WIN;
        }

        return WinLose.LOSE;
    }

    @Override
    public void drawCard(CardProvidable cardProvidable) {
        cardPossessable.drawCard(cardProvidable);
    }

    @Override
    public Calculatable calculateScore() {
        return cardPossessable.calculateScore();
    }

    @Override
    public abstract boolean canDrawMore();

    public CardPossessable getCardsOnHand() {
        return cardPossessable;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer that = (Gamer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
