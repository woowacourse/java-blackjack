package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.Status;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public abstract class Player {
    protected static final int START_INDEX = 0;
    public static final int INITIAL_CARDS_SIZE = 2;

    protected Cards cards;
    protected String name;

    public Player() {
        this.cards = new Cards();
    }

    public void addCard(CardDeck deck) {
        this.cards.add(deck.pop());
    }

    public void distributeInitialCards(CardDeck cardDeck) {
        IntStream.range(START_INDEX, INITIAL_CARDS_SIZE)
                .forEach(i -> this.addCard(cardDeck));

    }

    public void changeStatus() {
        this.cards.changeStatus();
    }

    public int calculateScore() {
        return this.cards.calculateScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return this.cards.getStatus();
    }

    public int getCardsSize() {
        return this.cards.size();
    }

    public abstract List<Card> getInitialCards();
}
