package blackjack.domain;

import blackjack.domain.card.Hand;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public List<Outcome> getDealerOutcome(final Referee referee) {
        return Outcome.reverse(calculateOutcomes(referee));
    }

    private List<Outcome> calculateOutcomes(final Referee referee) {
        final List<Outcome> outcomes = new ArrayList<>();
        for (final Player player : gamers.getPlayers().getPlayers()) {
            outcomes.add(calculateOutcome(referee, player.getName()));
        }
        return outcomes;
    }

    public Map<Player, Outcome> getPlayersOutcome(final Referee referee) {
        Map<Player, Outcome> playerOutcomeMap = new LinkedHashMap<>();
        for (final Player player : gamers.getPlayers().getPlayers()) {
            playerOutcomeMap.put(player, calculateOutcome(referee, player.getName()));
        }
        return playerOutcomeMap;
    }

    private Outcome calculateOutcome(final Referee referee, final Name name) {
        final Player player = gamers.getPlayers().findByName(name);
        return referee.doesPlayerWin(player.getHand());
    }

    public Dealer getDealer() {
        return gamers.getDealer();
    }

    public Hand getDealerHand() {
        return gamers.getDealer().getHand();
    }

    public List<Player> getPlayers() {
        return gamers.getPlayers().getPlayers();
    }
}
