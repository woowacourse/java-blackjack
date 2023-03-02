import deck.Deck;
import player.Dealer;
import player.Name;
import player.Player;
import player.Players;

public class BlackjackGame {
    public static final int FIRST_DRAW_COUNT = 2;
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void addPlayer(String playerName) {
        players.add(new Player(new Name(playerName)));
    }

    public void supplyCardsToDealer() {
        for (int i = 0; i < FIRST_DRAW_COUNT; i++) {
            dealer.hit(deck.drawCard());
        }
    }
}
