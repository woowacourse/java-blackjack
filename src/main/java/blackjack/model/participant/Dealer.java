package blackjack.model.participant;

import blackjack.model.MatchResult;
import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import blackjack.model.card.RandomCardShuffler;
import blackjack.model.state.State;
import blackjack.model.state.finished.FinishedState;
import blackjack.model.state.running.DealerDrawing;
import java.util.List;

public final class Dealer implements CardReceivable {

    public static final int DEALER_HIT_THRESHOLD = 16;

    private final Deck deck;
    private State state;

    public Dealer(Deck deck) {
        state = new DealerDrawing();
        this.deck = deck;
    }

    public static Dealer createWithShuffledStandardDeck() {
        return new Dealer(Deck.createStandardDeck(new RandomCardShuffler()));
    }

    public void dealCard(CardReceivable cardReceivable) {
        cardReceivable.receiveCard(drawFromDeck());
    }

    private Card drawFromDeck() {
        return deck.draw();
    }

    @Override
    public void receiveCard(Card card) {
        state = state.receiveCard(card);
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public Card getVisibleCard() {
        if (state.getHandCards().isEmpty()) {
            throw new IllegalStateException("딜러가 가진 패가 없습니다.");
        }
        return state.getHandCards()
                .getFirst();
    }

    public MatchResult evaluateOutcome(Player player) {
        FinishedState dealerState = getFinishedState();
        FinishedState playerState = player.getFinishedState();
        return dealerState.determineMatchResult(playerState);
    }

    private FinishedState getFinishedState() {
        if (!(state instanceof FinishedState finishedState)) {
            throw new IllegalArgumentException("딜러의 턴이 종료되지 않았습니다.");
        }
        return finishedState;
    }

    public List<Card> getHandCards() {
        return state.getHandCards();
    }

    public int getTotal() {
        return state.getTotal();
    }
}
