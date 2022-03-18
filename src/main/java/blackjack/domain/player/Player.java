package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

public abstract class Player {

    private final Name name;
    private final State state;

    public Player(Name name, CardDeck cardDeck) {
        this.name = name;
        this.state = new Ready()
            .draw(cardDeck.draw())
            .draw(cardDeck.draw());
    }
    public abstract boolean isHit();

    public void addCard(Card card) {
        state.draw(card);
    }

    public Cards getCardsByState(){
        return state.cards();
    }
    public int getPoint() {
        return state.cards().sum();
    }

    public boolean isBust() {
        return state.cards().isBust();
    }

    public String getName() {
        return name.getName();
    }
}
