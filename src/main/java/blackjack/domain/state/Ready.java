package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Ready implements State {

    private final PlayingCards playingCards = new PlayingCards();
    private Betting betting;

    public void bet(final Betting betting) {
        this.betting = betting;
    }

    private void validateBetting() {
        if (betting == null) {
            throw new IllegalStateException("베팅 금액을 입력 해야 합니다.");
        }
    }

    @Override
    public State draw(final Card card) {
        validateBetting();
        playingCards.add(card);

        if (playingCards.isMoreDeal()) {
            return this;
        }
        if (playingCards.isBlackjack()) {
            return new Blackjack(playingCards, betting);
        }
        return new Hit(playingCards, betting);
    }

    @Override
    public State stay() {
        validateBetting();
        return new Stay(playingCards, betting);
    }

    @Override
    public PlayingCards playingCards() {
        return playingCards;
    }
}
