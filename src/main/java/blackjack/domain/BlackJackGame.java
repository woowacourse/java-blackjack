package blackjack.domain;

public class BlackJackGame {
    private static final int INIT_DRAW_CARD_COUNT = 2;
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackJackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void initDraw() {
        for (int i = 0; i < INIT_DRAW_CARD_COUNT; i++) {
            players.receiveCard(deck);
            dealer.receiveCard(deck.draw());
        }
    }

}
