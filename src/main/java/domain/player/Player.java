package domain.player;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import domain.score.Score;

import java.util.List;

public class Player {

    private final PlayerName playerName;
    private final Cards cards;

    public Player(final PlayerName playerName) {
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

    public Status compareWithDealer(final Dealer dealer) {
        if (isBothBlackjack(dealer)) {
            return Status.DRAW;
        }
        if (this.isBlackjack()) {
            return Status.BLACKJACK_WIN;
        }
        if (dealer.isBlackjack() || this.isBusted()) {
            return Status.LOSE;
        }
        if (dealer.isBusted()) {
            return Status.WIN;
        }
        return this.compareNormalCase(dealer.getScore());
    }

    private Status compareNormalCase(final Score score) {
        return getScore().compareScore(score);
    }

    private boolean isBothBlackjack(final Dealer dealer) {
        return dealer.isBlackjack() && this.isBlackjack();
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
