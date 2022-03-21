package blackjack.domain.state;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;

public class Ready extends Running {

    public Ready() {
        this(new PlayingCards());
    }

    public Ready(final PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public State draw(PlayingCard playingCard) {
        PlayingCards playingCards = playingCards().addCard(playingCard);
        if (playingCards.isReady()) {
            return new Ready(playingCards);
        }
        if (playingCards.isBlackjack()) {
            return new Blackjack(playingCards);
        }
        return new Hit(playingCards);
    }

    @Override
    public State stay() {
        return new Stay(playingCards());
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
