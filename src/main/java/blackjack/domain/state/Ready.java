package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Ready extends Running {

    Ready(final PlayingCards playingCards) {
        super(playingCards);
    }

    public Ready() {
        this(new PlayingCards());
    }

    private void validateNumber(final String string) {
        if (!string.matches("-?[0-9]+")) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

    @Override
    public void bet(final String betting) {
        validateNumber(betting);
        this.betting = new Betting(betting);
    }

    @Override
    public State draw(final Card card) {
        playingCards.add(card);

        if (playingCards.isMoreDeal()) {
            return this;
        }
        if (playingCards.isBlackjack()) {
            return new Blackjack(playingCards, betting);
        }
        return new Hit(playingCards, betting);
    }
}
