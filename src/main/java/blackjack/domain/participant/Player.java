package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Score;
import java.util.List;

public final class Player implements Decidable {

    private static final int PLAYER_MAX_RECEIVE_CARD = 21;

    private final Participant participant;
    private Money bettingMoney;

    public Player(final String name) {
        this.participant = new Participant(Hand.generateEmptyCards(), name);
        this.bettingMoney = Money.init();
    }

    public void receiveCard(final Card card) {
        participant.receiveCard(card);
    }

    public Score calculateTotalScore() {
        return participant.calculateTotalScore();
    }

    public boolean isBust() {
        return participant.isBust();
    }

    public boolean isBlackjack() {
        return participant.isBlackjack();
    }

    public void betting(final int bettingMoney) {
        this.bettingMoney = new Money(bettingMoney);
    }

    @Override
    public List<Card> showInitCards() {
        return participant.getHand();
    }

    @Override
    public boolean canDraw() {
        Score score = participant.calculateTotalScore();
        return score.isLessOrEquals(new Score(PLAYER_MAX_RECEIVE_CARD));
    }

    public List<Card> getHand() {
        return participant.getHand();
    }

    public String getName() {
        return participant.getName();
    }

    public Money getBettingMoney() {
        return bettingMoney;
    }
}
