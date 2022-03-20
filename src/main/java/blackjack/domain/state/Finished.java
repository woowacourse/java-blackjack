package blackjack.domain.state;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;

public abstract class Finished implements State {

    private static final String ALREADY_FINISH_ERROR_MASSAGE = "[Error]: 카드 분배가 종료된 상태입니다.";

    private final PlayingCards playingCards;

    public Finished(final PlayingCards playingCards) {
        this.playingCards = playingCards;
    }

    @Override
    public final PlayingCards playingCards() {
        return playingCards;
    }

    @Override
    public final State draw(PlayingCard playingCard) {
        throw new IllegalStateException(ALREADY_FINISH_ERROR_MASSAGE);
    }

    @Override
    public final State stay() {
        throw new IllegalStateException(ALREADY_FINISH_ERROR_MASSAGE);
    }

    @Override
    public final boolean isFinished() {
        return true;
    }
}
