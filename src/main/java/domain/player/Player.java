package domain.player;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import domain.state.State;
import java.util.Objects;

public abstract class Player {

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

    public boolean isRunning() {
        return !state.isFinished();
    }

    public boolean isBlackjack() {
        return state.cards().isBlackjack();
    }

    public boolean isBust() {
        return state.cards().isBust();
    }

    public int computeOptimalSum() {
        return state.cards().computeOptimalSum();
    }

    public void drawCards(Deck deck, int count) {
        for (int i = 0; i < count; i++) {
            hit(deck);
        }
    }

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
                .findAny()
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

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Player player)) {
            return false;
        }
        return Objects.equals(name, player.name) && Objects.equals(state, player.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, state);
    }
}
