package blackjack.domain.participant;

import static blackjack.domain.BlackjackScoreRule.DEALER_ENABLE_MINIMUM_SCORE;

import java.util.List;

import blackjack.domain.card.Card;

public final class Dealer extends Participant {

    private Dealer(final List<Card> cards) {
        super(cards);
        changeToStandIfPossible();
    }

    public static Dealer readyToPlay(final List<Card> cards) {
        return new Dealer(cards);
    }

    private void changeToStandIfPossible() {
        if (isDealerCannotDrawCardAnymore()) {
            this.state = state.stay();
        }
    }

    private boolean isDealerCannotDrawCardAnymore() {
        return state.isPossibleToDrawCard() && DEALER_ENABLE_MINIMUM_SCORE.isNotOverThan(state.getScore());
    }

    public void drawCard(final Card card) {
        this.state = state.drawCard(card);
        changeToStandIfPossible();
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
