package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Bust implements State {

    private final PlayingCards playingCards;

    public Bust(final PlayingCards playingCards) {
        this.playingCards = playingCards;
    }

    @Override
    public State draw(final Card card) {
        throw new IllegalStateException("Bust 상태일 때는 draw 를 실행할 수 없습니다.");
    }
}
