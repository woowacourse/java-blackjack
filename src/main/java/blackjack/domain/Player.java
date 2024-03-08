package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Player {
    private final PlayerName playerName;
    private final PlayerCards playerCards;

    public Player(PlayerName playerName) {
        this.playerName = playerName;
        this.playerCards = PlayerCards.createEmptyCards();
    }

    public static Player fromName(String name) {
        return new Player(new PlayerName(name));
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
