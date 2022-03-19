package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.BlackjackScoreRule;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.state.State;

public final class Dealer extends Participant {

    private Dealer(final Deck deck) {
        super(deck);
    }

    public static Dealer readyToPlay(final Deck deck) {
        return new Dealer(deck);
    }

    public void drawCard(final Card card) {
        this.state = considerState(card);
    }

    private State considerState(final Card card) {
        if (isDealerCannotDrawCardAnymore()) {
            return state.stay();
        }
        return state.drawCard(card);
    }

    private boolean isDealerCannotDrawCardAnymore() {
        return state.isPossibleToDrawCard() &&
                (BlackjackScoreRule.DEALER_ENABLE_MINIMUM_SCORE.isNotOverThan(state.getScore()));
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
