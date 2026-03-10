package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {
    // TODO: protected를 굳이? 그리고 List<Card>를 일급컬렉션으로 빼보자 (2026. 3. 9.)
    protected final List<Card> cards;

    public Participant() {
        this.cards = new ArrayList<>();
    }

    public final void receiveCard(Card card) {
        cards.add(card);
    }

    public final Score getScore() {
        return ScoreCalculator.calculate(cards);
    }

    public final boolean isBurst() {
        return getScore().isBurst();
    }

    public final Card getFirstCardName() {
        return cards.getFirst();
    }

    public final List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public abstract boolean canReceive();
}
