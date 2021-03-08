package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.view.OutputView;
import java.util.stream.IntStream;

public class GameTable {

    private static final int FIRST_DRAW_COUNT = 2;

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public GameTable(Dealer dealer, Players players) {
        deck = new Deck();
        this.dealer = dealer;
        this.players = players;
    }

    public void drawAtFirst() {
        IntStream.range(0, FIRST_DRAW_COUNT)
            .forEach(index -> {
                draw(dealer);
                drawPlayers(players);
            });
        OutputView.printShowUsersCardMessage(players);
        OutputView.printUsersFirstDrawCards(dealer, players);
    }

    public void draw(User user) {
        user.hit(deck.pop());
    }

    private void drawPlayers(Players players) {
        players.getPlayers()
            .forEach(this::draw);
    }
}
