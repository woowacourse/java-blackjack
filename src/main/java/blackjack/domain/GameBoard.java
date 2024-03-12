package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameBoard {

    private final Dealer dealer;
    private final Players players;

    public GameBoard(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void drawInitialDealerHand() {
        dealer.draw();
        dealer.draw();
    }

    public void drawInitialPlayersHand() {
        players.drawInitialHand(dealer);
    }

    public Card getDealerFirstCard() {
        return dealer.openFirstCard();
    }

    public void hitDealer() {
        dealer.draw();
    }

    public void hitPlayer(final Player player) {
        player.draw(dealer.drawPlayerCard());
    }

    public boolean canDealerHit() {
        return dealer.canDraw();
    }

    public boolean canPlayerHit(final Player player) {
        return player.canDraw();
    }

    public List<Outcome> getDealerOutcome() {
        final List<Outcome> dealerOutcomes = new ArrayList<>();
        for (final Player player : players.getPlayers()) {
            final Outcome playerOutcome = Outcome.doesPlayerWin(dealer, player);
            dealerOutcomes.add(Outcome.reverse(playerOutcome));
        }
        return Collections.unmodifiableList(dealerOutcomes);
    }

    public Map<Name, Outcome> getPlayerOutcomes() {
        final Map<Name, Outcome> playerOutcomes = new LinkedHashMap<>();
        for (final Player player : players.getPlayers()) {
            final Outcome playerOutcome = Outcome.doesPlayerWin(dealer, player);
            playerOutcomes.put(player.getName(), playerOutcome);
        }
        return Collections.unmodifiableMap(playerOutcomes);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
