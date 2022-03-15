package blackjack.domain.player;

import blackjack.domain.GameResult;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;
import java.util.List;

public abstract class Player {

    private final String name;
    private final PlayingCards playingCards;

    protected Player(final String name) {
        this.name = name;
        this.playingCards = new PlayingCards();
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
