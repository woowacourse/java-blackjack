package blackjack.player;

import blackjack.card.Card;
import blackjack.player.state.DealerState;
import blackjack.player.state.GameState;
import blackjack.player.state.PlayerState;
import java.util.List;
import java.util.function.Supplier;

public class Player {

    private final Name name;
    private GameState state;

    public Player(String name) {
        this(name, new PlayerState());
    }

    private Player(String name, GameState state) {
        this.name = new Name(name);
        this.state = state;
    }

    public static Player createAsDealer() {
        return new Player("딜러", new DealerState());
    }

    public void drawCard(Card card) {
        state = state.drawCard(card);
    }

    public void drawCards(Supplier<Card> cardSupplier, int amount) {
        for (int i = 0; i < amount; i++) {
            drawCard(cardSupplier.get());
        }
    }

    public boolean isDrawable() {
        return !state.isTerminated();
    }

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return state.getScore();
    }

    public List<Card> getCards() {
        return state.cards();
    }
}
