package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;
import java.util.List;

public abstract class Player {


    protected final PlayerCards playerCards;

    protected Player(final PlayerCards playerCards) {
        this.playerCards = playerCards;
    }

    public void receiveCard(final Card card) {
        playerCards.save(card);
    }

    public List<Card> showCards() {
        return List.copyOf(playerCards.getCards());
    }

    public int calculateResult() {
        return playerCards.calculateTotalPoint();
    }

    public boolean isBlackJack() {
        return playerCards.isBlackJack();
    }

    public boolean isBust() {
        return playerCards.isBust();
    }

    public abstract List<Card> openCards();

    public abstract boolean isReceivable();

}
