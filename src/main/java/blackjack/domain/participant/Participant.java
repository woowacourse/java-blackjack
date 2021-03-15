package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.state.HandState;
import blackjack.domain.participant.state.InitialState;
import blackjack.domain.result.Result;

import java.util.Objects;

public abstract class Participant {
    protected final String name;
    protected HandState hand;
    protected Money money;

    public Participant(final String name, final int money) {
        validateName(name);
        validateMoney(money);
        this.name = name;
        this.money = new Money(money);
        this.hand = new InitialState();
    }

    private void validateName(final String name) {
        if (Objects.isNull(name) || name.length() == 0) {
            throw new IllegalArgumentException("이름으로 최소 한 글자가 입력되어야 합니다.");
        }
    }

    private void validateMoney(final int money) {
        if (money < 0) {
            throw new IllegalArgumentException("베팅 금액은 0 이상이여야 합니다.");
        }
    }

    public final void receiveInitialCard(final CardDeck cardDeck) {
        hand = hand.add(cardDeck.distribute());
        hand = hand.add(cardDeck.distribute());
    }

    public final void receiveAdditionalCard(final Card card) {
        hand = hand.add(card);
    }

    public final boolean isBust() {
        return hand.isBust();
    }

    public final boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public final int calculateScore() {
        return hand.calculateScore();
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

    public final HandState getHand() {
        return hand;
    }
}
