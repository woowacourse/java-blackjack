package blackjack.domain;

public class GameManager {
    public static final int INITIAL_DRAWING_COUNT = 2;

    private final Deck deck;
    private final Players players;

    public GameManager(Deck deck, Players players) {
        this.deck = deck;
        this.players = players;
    }

    public void giveCards() {
        for (int i = 0; i < INITIAL_DRAWING_COUNT; i++) {
            players.giveCards(deck);
        }
    }
}