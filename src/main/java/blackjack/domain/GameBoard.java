package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.BetAmount;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameBoard {

    private final Deck deck;
    private final Gamers gamers;

    public GameBoard(final Deck deck, final Gamers gamers) {
        this.deck = deck;
        this.gamers = gamers;
    }

    public void drawInitialCards() {
        gamers.drawInitialCards(deck);
    }

    public void hit(final Gamer gamer) {
        gamer.draw(deck.pop());
    }

    public boolean canHit(final Gamer gamer) {
        return gamer.canHit();
    }

    public double getDealerProfit() {
        return gamers.getPlayers().getPlayers().stream()
                .mapToDouble(player -> GameResult.calculateDealerProfit(getDealer(), player))
                .sum();
    }

    public Map<Name, BetAmount> getPlayersProfit() {
        return gamers.getPlayers().getPlayers().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> new BetAmount(GameResult.calculatePlayerProfit(getDealer(), player)),
                        (p1, p2) -> p1,
                        LinkedHashMap::new
                ));
    }

    public Dealer getDealer() {
        return gamers.getDealer();
    }

    public List<Player> getPlayers() {
        return gamers.getPlayers().getPlayers();
    }
}
