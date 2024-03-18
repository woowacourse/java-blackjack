package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.state.InitialParticipantState;
import blackjack.domain.state.ParticipantState;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private static final int STAND_LIMIT_VALUE = 17;
    private static final String DEFAULT_NAME = "딜러";

    public Dealer(Name name, ParticipantState participantState) {
        super(name, participantState);
    }

    public static Dealer createInitialStateDealer() {
        return new Dealer(new Name(DEFAULT_NAME), new InitialParticipantState());
    }

    @Override
    public Dealer draw(Deck deck) {
        return new Dealer(getName(), drawHand(deck));
    }

    @Override
    public Dealer stand() {
        return new Dealer(getName(), standHand());
    }

    public Dealer takeFirstHand(Deck deck) {
        return draw(deck);
    }

    public Dealer decideHitOrStand(Deck deck) {
        if (canDraw()) {
            return draw(deck);
        }
        return stand();
    }

    @Override
    public boolean canDraw() {
        return !isFinished() && isDealerScoreUnderLimit(calculateHand());
    }

    private boolean isDealerScoreUnderLimit(Score score) {
        return Score.from(STAND_LIMIT_VALUE).isBiggerThan(score);
    }

    public List<Card> getVisibleCards() {
        List<Card> cards = getCards();
        return new ArrayList<>(cards.subList(0, cards.size() - 1));
    }
}
