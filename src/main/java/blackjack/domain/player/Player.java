package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.Status;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public abstract class Player {
    public static final int INITIAL_CARDS_SIZE = 2;
    public static final int BLACKJACK_SCORE = 21;
    protected static final int START_INDEX = 0;

    protected Cards cards;
    protected String name;
    private Status status;

    public Player() {
        this.cards = new Cards();
        this.status = Status.NONE;
    }

    public void distributeInitialCards(CardDeck cardDeck) {
        IntStream.range(START_INDEX, INITIAL_CARDS_SIZE)
                .forEach(i -> this.addCard(cardDeck));
    }

    public void addCard(CardDeck deck) {
        this.cards.add(deck.pop());
    }

    public void changeStatusIfBlackJack() {
        if (calculateScore() == BLACKJACK_SCORE) {
            this.status = Status.BLACKJACK;
        }
    }

    public void changeStatusIfBust(){
        if (calculateScore() > BLACKJACK_SCORE) {
            this.status = Status.BUST;
        }
    }

    public int calculateScore() {
        return this.cards.calculateScore();
    }

    public boolean isNoneStatus() {
        return this.status == Status.NONE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    public int getCardsSize(){
        return this.cards.size();
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return this.status;
    }

    public abstract List<Card> getInitialCards();
}
