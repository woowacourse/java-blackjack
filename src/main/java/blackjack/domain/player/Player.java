package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.player.state.BlackJack;
import blackjack.domain.player.state.Bust;
import blackjack.domain.player.state.State;
import blackjack.domain.player.state.StateFactory;
import java.util.List;

public abstract class Player {

    private final String name;
    private final int batMoney;
    private State state;

    protected Player(String name, int batMoney, Card first, Card second) {
        this.name = name;
        this.batMoney = batMoney;
        this.state = StateFactory.start(Deck.of(first, second));
    }

    public void drawCard(Card card) {
        state = state.draw(card);
    }

    public Score score() {
        return state.score();
    }

    public boolean isBlackJack() {
        return state instanceof BlackJack;
    }

    public boolean isBust() {return state instanceof Bust; }

    public String name() {
        return name;
    }

    public State state() {
        return state;
    }

    public int winningMoney() {
        return state.winningMoney(batMoney);
    }

    public int batMoney() {
        return batMoney;
    }

    public List<Card> cards() {
        return state.cards();
    }

    public abstract boolean drawable();

}
