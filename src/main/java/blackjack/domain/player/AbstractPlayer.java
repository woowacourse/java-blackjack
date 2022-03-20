package blackjack.domain.player;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;

public abstract class AbstractPlayer implements Player {

    private static final int BLACKJACK_SIZE = 2;

    protected String name;
    protected PlayingCards playingCards;

    protected AbstractPlayer(String name, PlayingCards playingCards) {
        this.name = name;
        this.playingCards = playingCards;
    }

    @Override
    public final void addCard(PlayingCard playingCard) {
        playingCards.addCard(playingCard);
    }

    @Override
    public final boolean isLose(Player player) {
        return player.getPlayingCards().calculatePoints() > playingCards.calculatePoints();
    }

    @Override
    public final boolean isDraw(Player player) {
        return playingCards.calculatePoints() == player.getPlayingCards().calculatePoints();
    }

    @Override
    public final boolean isBlackJack(Player competitor) {
        return (playingCards.getCards().size() == BLACKJACK_SIZE &&
                playingCards.calculatePoints() == PlayingCards.BLACKJACK_POINT)
                &&
                !(competitor.getPlayingCards().getCards().size() == BLACKJACK_SIZE &&
                competitor.getPlayingCards().calculatePoints() == PlayingCards.BLACKJACK_POINT);
    }

    @Override
    public final boolean isBust() {
        return playingCards.calculatePoints() > PlayingCards.BLACKJACK_POINT;
    }

    @Override
    public boolean isHit() {
        return playingCards.calculatePoints() < limitHit();
    }

    protected abstract int limitHit();


    @Override
    public String getName() {
        return name;
    }

    @Override
    public PlayingCards getPlayingCards() {
        return playingCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractPlayer that = (AbstractPlayer) o;

        return playingCards != null ? playingCards.equals(that.playingCards) : that.playingCards == null;
    }

    @Override
    public int hashCode() {
        return playingCards != null ? playingCards.hashCode() : 0;
    }
}
