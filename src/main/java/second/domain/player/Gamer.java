package second.domain.player;

import second.domain.ICardDeck;
import second.domain.IPlayer;
import second.domain.card.HandCards;
import second.domain.card.Score;

public abstract class Gamer implements IPlayer {
    private final String name;
    private final HandCards handCards;

    public Gamer(String name, HandCards handCards) {
        this.name = name;
        this.handCards = handCards;
    }

    public boolean isSmallerOrEqualThan(final Score score) {
        return !handCards.isLargerScoreThan(score);
    }

    public boolean isLargerScoreThan(final Gamer Gamer) {
        return handCards.isLargerScoreThan(Gamer.handCards);
    }

    @Override
    public abstract boolean canDrawMore();

    @Override
    public void drawCard(ICardDeck cardDeck) {
        handCards.drawCard(cardDeck);
    }

    @Override
    public boolean isBust() {
        return handCards.isBust();
    }

    public String getName() {
        return name;
    }

    public HandCards getHandCards() {
        return handCards;
    }
}
