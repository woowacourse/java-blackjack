package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Score;
import java.util.List;

public final class Player extends Participant {

    private static final int PLAYER_MAX_RECEIVE_CARD = 21;

    private Money bettingMoney;

    public Player(final String name) {
        super(Hand.generateEmptyCards(), name);
        this.bettingMoney = Money.init();
    }

    public void betting(final int bettingMoney) {
        this.bettingMoney = new Money(bettingMoney);
    }

    public Money getBettingMoney() {
        return bettingMoney;
    }

    @Override
    public List<Card> showInitCards() {
        return getHand();
    }

    @Override
    public boolean canDraw() {
        Score score = calculateTotalScore();
        return score.canDraw(new Score(PLAYER_MAX_RECEIVE_CARD));
    }
}
