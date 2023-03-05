package domain.player;

import domain.score.Score;
import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public class Player {

    private final PlayerName playerName;
    private final Cards cards;

    public Player(PlayerName playerName) {
        this.playerName = playerName;
        this.cards = new Cards();
    }

    public void drawCard(Card card) {
        cards.addCard(card);
    }

    public Score getScore() {
        return cards.calculateScore();
    }

    public boolean isBusted() {
        return getScore().isBusted();
    }

    public boolean isNotBusted() {
        return !getScore().isBusted();
    }

    public boolean isBlackjack() {
        return cards.isBlackJack();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public String getName() {
        return playerName.getName();
    }

}
