package domain.gamer;

import domain.card.Card;
import domain.card.cardDrawable.Hand;
import domain.card.providable.CardProvidable;
import domain.result.Score;
import domain.result.WinLose;

import java.util.List;
import java.util.Objects;

import static domain.result.ScoreCalculable.BLACK_JACK_SCORE;

public abstract class AbstractGamer implements Gamer {
    protected final Hand hand;
    private final String name;

    AbstractGamer(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    @Override
    public void drawCard(CardProvidable cardProvidable) {
        hand.drawCard(cardProvidable);
    }

    @Override
    public List<Card> showAllCards() {
        return hand.getCards();
    }

    @Override
    public Score calculateScore() {
        return hand.calculateScore();
    }

    @Override
    public WinLose determineWinLose(Gamer counterPart) {
        if (this.calculateScore().isBiggerThan(new Score(BLACK_JACK_SCORE))) {
            return WinLose.LOSE;
        }

        if (counterPart.calculateScore().isBiggerThan(new Score(BLACK_JACK_SCORE))) {
            return WinLose.WIN;
        }

        if (WinLose.determineWinner(this, counterPart) == this) {
            return WinLose.WIN;
        }

        return WinLose.LOSE;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractGamer that = (AbstractGamer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
