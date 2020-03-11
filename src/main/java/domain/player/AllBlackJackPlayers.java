package domain.player;

import domain.CardProvider;

import java.util.Collections;
import java.util.List;

public class AllBlackJackPlayers {
    private static final int INITIAL_CARD_AMOUNT = 2;

    private final List<BlackJackPlayer> blackJackPlayers;

    public AllBlackJackPlayers(List<BlackJackPlayer> blackJackPlayers) {
        this.blackJackPlayers = blackJackPlayers;
    }

    public void drawFirstPhase(CardProvider cardProvider) {
        for (BlackJackPlayer blackJackPlayer : blackJackPlayers) {
            drawInitialCardToEach(blackJackPlayer, cardProvider);
        }
    }

    private void drawInitialCardToEach(BlackJackPlayer blackJackPlayer, CardProvider cardProvider) {
        for (int i = 0; i < INITIAL_CARD_AMOUNT; i++) {
            blackJackPlayer.drawCard(cardProvider);
        }
    }

    public List<BlackJackPlayer> getBlackJackPlayers() {
        return Collections.unmodifiableList(blackJackPlayers);
    }
}
