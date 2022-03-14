package blackjack.domain.player;

import blackjack.domain.GameResult;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;
import java.util.List;

public abstract class Player {

    private static final int BURST_NUMBER = 21;
    protected final String name;
    protected final PlayingCards playingCards = new PlayingCards();

    protected Player(final String name) {
        this.name = name;
    }

    public void receiveCard(final PlayingCard playingCard) {
        playingCards.addCard(playingCard);
    }

    public abstract boolean isFinished();

    public boolean isBurst() {
        return playingCards.getCardSum() > BURST_NUMBER;
    }

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
