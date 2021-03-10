package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.result.Result;

import java.util.Objects;

public abstract class Participant {
    protected final Hand hand;
    private final String name;

    public Participant(final String name) {
        validateName(name);
        this.name = name;
        this.hand = new Hand();
    }

    private void validateName(final String name) {
        if (Objects.isNull(name) || name.length() == 0) {
            throw new IllegalArgumentException("이름으로 최소 한 글자가 입력되어야 합니다.");
        }
    }

    public final void receiveInitialCard(final CardDeck cardDeck) {
        hand.add(cardDeck.distribute());
        hand.add(cardDeck.distribute());
    }

    public final void receiveAdditionalCard(final Card card) {
        hand.add(card);
    }

    public final boolean isBust() {
        return hand.isBust();
    }

    abstract public Result generateResult(final Participant participant);

    protected final Result generateResultByScore(final Participant participant) {
        final int score = this.hand.calculateScore();
        final int opponentScore = participant.hand.calculateScore();
        return Result.findResult(score, opponentScore);
    }

    public final String getName() {
        return name;
    }

    public final Hand getHand() {
        return hand;
    }
}
