package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Player {
    PlayerCards playerCards;

    public Player() {
        this.playerCards = PlayerCards.createEmptyCards();
    }

    public void draw(Deck deck) {
        Card card = deck.draw();
        playerCards.append(card);
    }

    public List<Card> getCards() {
        return playerCards.getCards();
    }

    public int getScore() {
        return playerCards.calculateScore();
    }
}
