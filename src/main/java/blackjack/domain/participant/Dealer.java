package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.BlackjackRule;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.state.PlayState;
import blackjack.domain.participant.state.finished.StandState;

public final class Dealer extends Participant {

    private static final int DEALER_DEFAULT_BETTING_AMOUNT = 1;

    private Dealer(final Deck deck) {
        super(deck);
        readyToHitStateWithinBet();
    }

    private void readyToHitStateWithinBet() {
        this.state = state.betAmount(DEALER_DEFAULT_BETTING_AMOUNT);
    }

    public static Dealer readyToPlay(final Deck deck) {
        return new Dealer(deck);
    }

    public void drawCard(final Deck deck) {
        this.state = considerState(deck);
    }

    private PlayState considerState(final Deck deck) {
        if (isDealerCannotDrawCardAnymore()) {
            return new StandState(state);
        }
        return state.drawCard(deck);
    }

    private boolean isDealerCannotDrawCardAnymore() {
        return state.isPossibleToDrawCard() &&
                (BlackjackRule.DEALER_ENABLE_MINIMUM_SCORE.isNotOverThan(state.getScore()));
    }

    public Card getFirstCard() {
        final List<Card> cards = state.getCards();
        validateCardNotEmpty(cards);
        return cards.get(0);
    }

    private void validateCardNotEmpty(final List<Card> cards) {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 존재하지 않습니다.");
        }
    }

}
