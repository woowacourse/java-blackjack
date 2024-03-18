package blackjack.domain;

import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.function.Consumer;
import java.util.function.Function;

public class Game {
    private final Dealer dealer;
    private final Players players;

    public Game(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void confirmPlayersHands(Deck deck, Function<Player, Boolean> askDraw,
                                    Consumer<Player> printPlayerHands) {
        for (Player player : players.getPlayers()) {
            askDrawToPlayer(player, deck, askDraw, printPlayerHands);
        }
    }

    private void askDrawToPlayer(Player player, Deck deck, Function<Player, Boolean> askDraw,
                                 Consumer<Player> printPlayerHands) {
        while (!player.isBust() && askDraw.apply(player)) {
            player.draw(deck);
            printPlayerHands.accept(player);
        }
    }

    public void confirmDealerHands(Deck deck, Consumer<String> printDealerDrawMessage) {
        dealer.confirmDealerHands(deck, printDealerDrawMessage);
    }

    public GameResult makeGameResult() {
        return GameResult.of(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
