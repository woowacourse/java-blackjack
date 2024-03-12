package blackjack.domain;

import blackjack.domain.card.Hand;
import blackjack.domain.card.Deck;
import blackjack.dto.OutcomeDto;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public GameBoard(final Deck deck, final Dealer dealer, final Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public Hand drawInitialDealerCards() {
        drawTwoCards(dealer);
        return new Hand(List.of(dealer.findFaceUpCard()));
    }

    public Players drawInitialPlayersCards() {
        for (Player player : players.getPlayers()) {
            drawTwoCards(player);
        }
        return players;
    }

    private void drawTwoCards(Gamer gamer) {
        gamer.draw(deck.pop());
        gamer.draw(deck.pop());
    }

    public void hit(Gamer gamer) {
        gamer.draw(deck.pop());
    }

    public boolean isHit(Gamer gamer) {
        return gamer.canDraw();
    }

    public List<Outcome> getDealerOutcomes() {
        final List<Outcome> playerOutcomes = new ArrayList<>();
        for (final Player player : players.getPlayers()) {
            playerOutcomes.add(Outcome.doesPlayerWin(dealer.getCards(), player.getCards()));
        }
        return Outcome.reverse(playerOutcomes);
    }

    public List<OutcomeDto> getPlayerOutcomeDtos() {
        final List<OutcomeDto> playerOutcomes = new ArrayList<>();
        for (final Player player : players.getPlayers()) {
            final Outcome playerOutcome = Outcome.doesPlayerWin(dealer.getCards(), player.getCards());
            playerOutcomes.add(new OutcomeDto(player.getName(), playerOutcome));
        }
        return playerOutcomes;
    }

    public Hand getDealerCards() {
        return dealer.getCards();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
