package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;

public class Player {
    private final PlayerName playerName;
    private final PlayerCards playerCards;

    public Player(String name) {
        this.playerName = new PlayerName(name);
        this.playerCards = PlayerCards.createEmptyCards();
    }

    public void draw(Deck deck) {
        Card card = deck.draw();
        playerCards.append(card);
    }

    public boolean isBusted() {
        return playerCards.isBusted();
    }

    public List<Card> getCards() {
        return playerCards.getCards();
    }

    public Score getScore() {
        return playerCards.calculateScore();
    }

    public int getScoreValue() {
        return getScore().value();
    }

    public String getName() {
        return playerName.name();
    }

    public int getTotalCardsCount() {
        return playerCards.size();
    }
}
