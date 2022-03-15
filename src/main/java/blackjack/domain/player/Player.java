package blackjack.domain.player;

import blackjack.domain.GameResult;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;
import java.util.List;

public abstract class Player {

    protected final String name;
    protected final PlayingCards playingCards = new PlayingCards();

    protected Player(final String name) {
        this.name = name;
    }

    public void receiveCard(final PlayingCard playingCard) {
        playingCards.addCard(playingCard);
    }

    public boolean isBurst() {
        return playingCards.isBurst();
    }

    public abstract boolean isNotFinished();

    public abstract boolean isDealer();

    public GameResult compare(final Player player) {
        return GameResult.of(this.getSumOfCards(), player.getSumOfCards());
    }

    public int getSumOfCards() {
        return playingCards.getCardSum();
    }

    public String getName() {
        return this.name;
    }

    public List<PlayingCard> getCards() {
        return playingCards.getPlayingCards();
    }
}
