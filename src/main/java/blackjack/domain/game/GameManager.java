package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Players;

public class GameManager {
    public static final int INITIAL_DRAWING_COUNT = 2;

    private final Players players;

    public GameManager(Players players) {
        this.players = players;
    }

    public void giveCards(Deck deck) {
        for (int i = 0; i < INITIAL_DRAWING_COUNT; i++) {
            players.giveCards(deck);
        }
    }
}