package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.dto.DealerDto;
import blackjack.domain.dto.OutcomeDto;
import blackjack.domain.dto.PlayersDto;
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

    public DealerDto drawInitialDealerCards() {
        drawTwoCards(dealer);
        return dealer.firstCardToDto();
    }

    public PlayersDto drawInitialPlayersCards() {
        for (final Player player : players.getPlayers()) {
            drawTwoCards(player);
        }
        return players.toDto();
    }

    private void drawTwoCards(final Gamer gamer) {
        gamer.draw(deck.pop());
        gamer.draw(deck.pop());
    }

    public void hit(final Gamer gamer) {
        gamer.draw(deck.pop());
    }

    public boolean isHit(final Gamer gamer) {
        return gamer.canDraw();
    }

    public DealerDto getDealerFinalState() {
        return dealer.allCardToDto();
    }

    public PlayersDto getPlayersFinalState() {
        return players.toDto();
    }

    public List<Outcome> getDealerOutcome(final Referee referee) {
        return Outcome.reverse(calculateOutcomes(referee));
    }

    private List<Outcome> calculateOutcomes(final Referee referee) {
        final List<Outcome> outcomes = new ArrayList<>();
        for (final Player player : players.getPlayers()) {
            outcomes.add(calculateOutcome(referee, player.getName()));
        }
        return outcomes;
    }

    public List<OutcomeDto> getPlayerOutcomeDtos(final Referee referee) {
        final List<OutcomeDto> playerOutcomes = new ArrayList<>();
        for (final Name name : players.getNames()) {
            playerOutcomes.add(new OutcomeDto(name, calculateOutcome(referee, name)));
        }
        return playerOutcomes;
    }

    private Outcome calculateOutcome(final Referee referee, final Name name) {
        final Player player = players.findByName(name);
        return referee.doesPlayerWin(player.getCards());
    }

    public Cards getDealerCards() {
        return dealer.getCards();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
