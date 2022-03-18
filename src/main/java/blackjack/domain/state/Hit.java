package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Hit extends Running {

    public Hit(final PlayingCards playingCards, final Betting betting) {
        super(playingCards);
        this.betting = betting;
    }

    @Override
    public void bet(final String betting) {
        throw new IllegalStateException("Hit 상태일 때는 bet 을 실행할 수 없습니다.");
    }

    @Override
    public State draw(final Card card) {
        playingCards.add(card);

        if (playingCards.isBust()) {
            return new Bust(playingCards, betting);
        }
        return this;
    }
}
