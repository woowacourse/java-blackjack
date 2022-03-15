package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.MatchStatus;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DRAWABLE_SCORE_LIMIT = 16;

    private Dealer(final Deck deck) {
        super(DEALER_NAME, deck);
    }

    public static Dealer readyToPlay(final Deck deck) {
        return new Dealer(deck);
    }

    @Override
    public boolean isPossibleToDrawCard() {
        return this.getScore() <= DRAWABLE_SCORE_LIMIT;
    }

    public MatchStatus judgeWinner(final Player player) {
        if (player.isBust()) {
            return MatchStatus.LOSS;
        }
        if (this.isBust()) {
            return MatchStatus.WIN;
        }
        return MatchStatus.from(this.isLowerThan(player));
    }

    private boolean isLowerThan(final Player player) {
        return this.getScore() < player.getScore();
    }

    public Card getFirstCard() {
        final List<Card> cards = this.getCards();
        validateCardNotEmpty(cards);
        return cards.get(0);
    }

    private void validateCardNotEmpty(final List<Card> cards) {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 존재하지 않습니다.");
        }
    }
}
