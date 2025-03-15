package domain.player;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import domain.state.State;

public abstract class Player {

    private static final int INITIAL_DRAW_COUNT = 2;

    protected final String name;
    protected State state;

    public Player(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public void hit(Deck deck) {
        this.state = state.hit(deck.drawCard());
    }

    public void stay() {
        this.state = state.stay();
    }

    public void drawInitialCards(Deck deck) {
        for (int count = 0; count < INITIAL_DRAW_COUNT; count++) {
            hit(deck);
        }
    }

    public abstract void openInitialCards();

    public void openCards(int count) {
        while (count > 0) {
            Card willBeOpened = findNotOpenedCard();
            willBeOpened.open();
            count--;
        }
    }

    private Card findNotOpenedCard() {
        return cards().getCards().stream()
                .filter(card -> !card.isOpened())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("오픈할 카드가 없습니다."));
    }

    public Cards openedCards() {
        return new Cards(
                cards().getCards().stream()
                        .filter(Card::isOpened)
                        .toList()
        );
    }

    public Cards cards() {
        return state.cards();
    }

    public String getName() {
        return name;
    }
}
