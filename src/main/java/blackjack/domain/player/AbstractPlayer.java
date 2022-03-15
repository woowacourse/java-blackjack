package blackjack.domain.player;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.Deck;

public abstract class AbstractPlayer implements Player {

    protected Deck playingCards;
    protected String name;

    @Override
    public void addCard(PlayingCard playingCard) {
        playingCards.addCard(playingCard);
    }

    @Override
    public boolean isBlackJack() {
        return playingCards.sumBlackJack();
    }

    @Override
    public boolean isLose(int point) {
        return point > playingCards.sumPoints();
    }

    @Override
    public boolean isOverPointLimit() {
        return playingCards.isBust();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Deck getDeck() {
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
