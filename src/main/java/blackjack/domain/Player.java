package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Player {
    private final String name; // TODO: 감싸기
    private final PlayerCards playerCards;

    public Player(String name) {
        this.name = name;
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
        return name;
    }
}
