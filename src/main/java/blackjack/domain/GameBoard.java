package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.dto.DealerDto;
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
        for (Player player : players.getPlayers()) {
            drawTwoCards(player);
        }
        return players.toDto();
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

    public List<Outcome> calculateOutcomes(final Referee referee) {
        final List<Outcome> outcomes = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            outcomes.add(calculateOutcome(referee, player.getName()));
        }
        return outcomes;
    }

    public Outcome calculateOutcome(final Referee referee, final Name name) {
        final Player player = players.findByName(name);
        return referee.doesPlayerWin(player.getCards());
    }
}
