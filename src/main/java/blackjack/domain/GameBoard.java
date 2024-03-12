package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.dto.OutcomeDto;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    private final Dealer dealer;
    private final Players players;

    public GameBoard(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Card drawInitialDealerHand() {
        final Card firstCard = dealer.draw();
        dealer.draw();
        return firstCard;
    }

    public Players drawInitialPlayersHand() {
        for (final Player player : players.getPlayers()) {
            player.draw(dealer.drawPlayerCard());
            player.draw(dealer.drawPlayerCard());
        }
        return players;
    }

    public void hit(final Dealer dealer) {
        dealer.draw();
    }

    public void hit(final Player player) {
        player.draw(dealer.drawPlayerCard());
    }

    public boolean isHit(Gamer gamer) {
        return gamer.canDraw();
    }

    public List<Outcome> getDealerOutcomes() {
        final List<Outcome> playerOutcomes = new ArrayList<>();
        for (final Player player : players.getPlayers()) {
            playerOutcomes.add(Outcome.doesPlayerWin(dealer.getHand(), player.getHand()));
        }
        return Outcome.reverse(playerOutcomes);
    }

    public List<OutcomeDto> getPlayerOutcomeDtos() {
        final List<OutcomeDto> playerOutcomes = new ArrayList<>();
        for (final Player player : players.getPlayers()) {
            final Outcome playerOutcome = Outcome.doesPlayerWin(dealer.getHand(), player.getHand());
            playerOutcomes.add(new OutcomeDto(player.getName(), playerOutcome));
        }
        return playerOutcomes;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
