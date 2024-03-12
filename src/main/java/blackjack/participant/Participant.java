package blackjack.participant;

import blackjack.card.Card;
import blackjack.participant.state.DealerState;
import blackjack.participant.state.GameState;
import blackjack.participant.state.PlayerState;
import java.util.List;
import java.util.function.Supplier;

public class Participant {

    private final Name name;
    private GameState state;

    public Participant(String name) {
        this(name, new PlayerState());
    }

    private Participant(String name, GameState state) {
        this.name = new Name(name);
        this.state = state;
    }

    public static Participant createAsDealer() {
        return new Participant("딜러", new DealerState());
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
