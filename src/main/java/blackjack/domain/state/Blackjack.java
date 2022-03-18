package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Blackjack extends Finished {

    private static final double PROFIT_RATE = 1.5;

    private final PlayingCards playingCards;
    private final Betting betting;

    public Blackjack(final PlayingCards playingCards, final Betting betting) {
        this.playingCards = playingCards;
        this.betting = betting;
    }

    @Override
    public State draw(final Card card) {
        throw new IllegalStateException("Blackjack 상태일 때는 draw 를 실행할 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("Blackjack 상태일 때는 stay 를 실행할 수 없습니다.");
    }

    @Override
    public double profit() {
        return betting.profit(PROFIT_RATE);
    }

    @Override
    public PlayingCards playingCards() {
        return playingCards;
    }
}
