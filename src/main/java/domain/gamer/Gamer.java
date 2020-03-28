package domain.gamer;

import domain.card.Card;
import domain.card.providable.CardProvidable;
import domain.gamer.action.TurnActions;
import domain.result.GameRule;
import domain.result.score.Score;
import domain.result.score.ScoreCalculable;

import java.util.List;
import java.util.Objects;

public abstract class Gamer {
    private static final int INITIAL_CARDS_AMOUNT = 2;

    protected final Hand hand;
    private final Name name;

    Gamer(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public abstract List<Card> openInitialCards();

    private void validateEmptyHand() {
        if (!hand.isEmpty()) {
            throw new IllegalStateException("손패가 비어있지 않습니다.");
        }
    }

    public void drawInitialCards(CardProvidable cardProvidable) {
        validateEmptyHand();

        for (int i = 0; i < INITIAL_CARDS_AMOUNT; i++) {
            hand.drawCard(cardProvidable);
        }
    }

    public void playTurn(CardProvidable cardProvidable, GameRule rule, TurnActions turnActions) {
        while (turnActions.isHit(this)) {
            hand.drawCard(cardProvidable);
            if (!rule.canDrawMore(this)) {
                break;
            }
            turnActions.showHand(this);
        }

        turnActions.showHand(this);
    }

    public List<Card> openAllCards() {
        return hand.getCards();
    }

    public Score calculateScore(ScoreCalculable scoreCalculable) {
        return scoreCalculable.calculateScore(this);
    }

    public Name getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
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
