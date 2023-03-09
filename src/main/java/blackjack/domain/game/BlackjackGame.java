package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;

public class BlackjackGame {
    private final Players players;
    private final Bets bets;

    public BlackjackGame(final Players players, final Bets bets) {
        this.players = players;
        this.bets = bets;
    }

    public BlackjackGame(final Bets bets) {
        this.players = Players.create();
        this.bets = bets;
    }

    public void addPlayers(final List<String> names) {
        players.addPlayers(names);
    }

    public void initialDraw(final Deck deck) {
        players.initialDraw(deck);
    }

    public void drawToDealer(final Deck deck) {
        players.drawToDealer(deck);
    }

    public void drawTo(final Name name, final Deck deck) {
        players.drawTo(name, deck);
    }

    public void drawTo(final Player player, Deck deck) {
        players.drawTo(player, deck);
    }

    public void stay(final Player player) {
        players.stay(player);
    }

    public Bets play() {
        bets.calculateProfit(players.play());
        return bets;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }
}
