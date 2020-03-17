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
    private static final int INITIAL_CARDS_AMOUNT = 2;

    protected final Hand hand;
    private final String name;

    AbstractGamer(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    @Override
    public void drawInitialCards(CardProvidable cardProvidable) {
        validateEmptyHand();

        for (int i = 0; i < INITIAL_CARDS_AMOUNT; i++) {
            this.drawCard(cardProvidable);
        }
    }

    private void validateEmptyHand() {
        if (!hand.isEmpty()) {
            throw new IllegalStateException("손패가 비어있지 않습니다.");
        }
    }

    @Override
    public void drawCard(CardProvidable cardProvidable) {
        hand.drawCard(cardProvidable);
    }

    @Override
    public List<Card> openAllCards() {
        return hand.getCards();
    }

    @Override
    public Score calculateScore() {
        return hand.calculateScore();
    }

    @Override
    public WinLose determineWinLose(Gamer counterPart) {
        Score myScore = this.calculateScore();
        Score counterPartScore = counterPart.calculateScore();

        if (myScore.isBiggerThan(new Score(BLACK_JACK_SCORE))) {
            return WinLose.LOSE;
        }

        if (counterPartScore.isBiggerThan(new Score(BLACK_JACK_SCORE))) {
            return WinLose.WIN;
        }

        return WinLose.determineWinLose(myScore, counterPartScore);
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
