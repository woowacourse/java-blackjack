package blackjack.domain.state;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;

public class Hit extends Running {

    Hit(final PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public State draw(PlayingCard playingCard) {
        PlayingCards playingCards = playingCards().addCard(playingCard);
        if (playingCards.isBust()) {
            return new Bust(playingCards());
        }
        if (playingCards.isMaxPoint()) {
            return new Stay(playingCards);
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
