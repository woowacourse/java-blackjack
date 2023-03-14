package domain.player;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import domain.score.Score;

import java.util.List;

public abstract class Gambler {

    private final PlayerName playerName;
    private final Cards cards;

    public Gambler(final PlayerName playerName) {
        this.playerName = playerName;
        this.cards = new Cards();
    }

    public void drawInitialCards(final Deck deck) {
        drawCard(deck.pickCard());
        drawCard(deck.pickCard());
    }

    public void drawCard(final Card card) {
        cards.addCard(card);
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

    public Score getScore() {
        return cards.calculateScore();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public String getName() {
        return playerName.getName();
    }
}
