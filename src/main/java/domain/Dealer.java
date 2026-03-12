package domain;

import domain.state.GameState;
import java.util.List;
import java.util.function.Supplier;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";

    private Dealer(GameState gameState) {
        super(DEALER_NAME, gameState);
    }

    public static Dealer from(GameState gameState) {
        return new Dealer(gameState);
    }

    @Override
    public List<Card> showInitialCard() {
        List<Card> ownCards = super.showOwnCards();
        return List.of(ownCards.getFirst());
    }

    public Dealer addCard(Supplier<Card> cardSupplier) {
        GameState newGameState = gameState.hit(cardSupplier);
        return Dealer.from(newGameState);
    }
}
