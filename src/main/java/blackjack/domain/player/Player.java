package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPack;

import java.util.List;
import java.util.Objects;

public abstract class Player {

    private static final int BUST_THRESHOLD = 21;
    private static final int HIT_THRESHOLD = 16;

    private final Name name;
    private final Hand hand;

    public Player(final String name, final int batMoney) {
        this.name = new Name(name);
        this.hand = new Hand(batMoney);
    }

    abstract public List<Card> getOpenedCards();

    public void pushDealCard(final CardPack cardPack, final int count) {
        hand.addCards(cardPack.getDealByCount(count));
    }

    public int compareWithOtherPlayer(final Player otherPlayer) {
        if (this.isBust() && otherPlayer.isNotBust()) {
            return -1;
        }
        if (this.isBust() && otherPlayer.isBust()) {
            return 0;
        }
        if (this.isNotBust() && otherPlayer.isBust()) {
            return 1;
        }
        return Integer.compare(this.getCardScore(), otherPlayer.getCardScore());
    }

    public boolean isBust() {
        return getCardScore() > BUST_THRESHOLD;
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isHit() {
        return hand.calculateCardScore() <= HIT_THRESHOLD;
    }

    public int getCardScore() {
        return hand.calculateCardScore();
    }

    public String getName() {
        return name.getName();
    }

    public Hand getCards() {
        return hand;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
