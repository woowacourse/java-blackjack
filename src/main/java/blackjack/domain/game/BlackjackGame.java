package blackjack.domain.game;

import blackjack.controller.BlackjackCommand;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.player.Result;

import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private final Players players;
    private final BettingZone bettingZone;

    public BlackjackGame(final Players players, final BettingZone bettingZone) {
        this.players = players;
        this.bettingZone = bettingZone;
    }

    public void drawInitialCards(Deck deck) {
        players.drawInitialCards(deck);
    }

    public void drawByDealer(Deck deck) {
        players.drawByDealer(deck);
    }

    public void drawByGambler(final Player player, final Deck deck, final BlackjackCommand command) {
        if (command.isHit()) {
            player.draw(deck);
            return;
        }
        player.stay();
    }

    public Map<Player, Result> play() {
        return players.play();
    }

    public Map<Player, Money> calculateBet() {
        return bettingZone.calculateProfitByPlayers(players.play());
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }
}
